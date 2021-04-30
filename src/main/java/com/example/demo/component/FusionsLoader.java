package com.example.demo.component;

import com.example.demo.model.Fusion;
import com.example.demo.model.LeaderSkill;
import com.example.demo.model.Monster;
import com.example.demo.repository.FusionsRepository;
import com.example.demo.repository.LeaderSkillRepository;
import com.example.demo.repository.MonsterRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class FusionsLoader {
    private final FusionsRepository fusionsRepository;
    private final MonsterRepository monsterRepository;

    public void loadAll() {
        fusionsRepository.deleteAll();
        List<Fusion> fusions = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/fusions/";
        RestTemplate restTemplate = new RestTemplate();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                Fusion fusion = new Fusion();
                fusion.setId(element.get("id").intValue());
                int productId = element.get("product").intValue();
                Optional<Monster> product = monsterRepository.findById(productId);
                if (product.isPresent()) {
                    fusion.setProduct(product.get());
                } else {
                    log.warn("Monster doesn't find: {}", productId);
                }

                Iterable<JsonNode> ingredientNodes = () -> element.get("ingredients").elements();
                List<Integer> ingredientIds = StreamSupport.stream(ingredientNodes.spliterator(), false)
                        .map(JsonNode::intValue)
                        .collect(Collectors.toList());

                List<Monster> ingredients = monsterRepository.findAllById(ingredientIds);
                if (ingredientIds.size() != ingredients.size()) {
                    log.warn("The size of ingredient's aren't similar: {}, ids: {}", ingredients.size(), ingredientIds.size());
                }

                fusion.setIngredients(ingredients);
                fusions.add(fusion);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        fusionsRepository.saveAll(fusions);
    }
}

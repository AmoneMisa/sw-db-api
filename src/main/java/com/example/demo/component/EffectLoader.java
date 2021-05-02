package com.example.demo.component;

import com.example.demo.model.Effect;
import com.example.demo.repository.EffectRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EffectLoader {
    private final EffectRepository effectRepository;

    public void loadAll() {
        List<Effect> skillEffects = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/skill-effects/";
        RestTemplate restTemplate = new RestTemplate();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                Effect skillEffect = new Effect();
                skillEffect.setId(element.get("id").intValue());
                skillEffect.setName(element.get("name").textValue());
                skillEffect.setDescription(element.get("description").textValue());
                skillEffect.setType(element.get("type").textValue());
                skillEffect.setIconName(element.get("icon_filename").textValue());

                skillEffects.add(skillEffect);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        effectRepository.saveAll(skillEffects);
    }

    public void preload() {
        effectRepository.deleteAll();
    }
}

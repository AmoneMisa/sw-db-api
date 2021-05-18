package com.example.demo.component;

import com.example.demo.model.LeaderSkill;
import com.example.demo.repository.LeaderSkillRepository;
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
public class LeaderSkillLoader {
    private final LeaderSkillRepository leaderSkillRepository;

    public void loadAll() {
        List<LeaderSkill> leaderSkills = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/leader-skills/";
        RestTemplate restTemplate = new RestTemplate();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                LeaderSkill leaderSkill = new LeaderSkill();
                leaderSkill.setId(element.get("id").intValue());
                leaderSkill.setAttribute(element.get("attribute").textValue());
                leaderSkill.setAmount(element.get("amount").intValue());
                leaderSkill.setArea(element.get("area").textValue());
                leaderSkill.setElement(element.get("element").textValue());


                leaderSkills.add(leaderSkill);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        leaderSkillRepository.saveAll(leaderSkills);
        log.info("Loaded leader skills");
    }

    public void preload() {
        leaderSkillRepository.deleteAll();
    }
}

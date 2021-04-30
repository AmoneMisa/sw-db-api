package com.example.demo.component;

import com.example.demo.model.Fusion;
import com.example.demo.model.HomunculusSkill;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import com.example.demo.repository.FusionsRepository;
import com.example.demo.repository.HomunculusSkillRepository;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.repository.SkillRepository;
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
public class HomunculusSkillLoader {
    private final HomunculusSkillRepository homunculusSkillRepository;
    private final SkillRepository skillRepository;

    public void loadAll() {
        homunculusSkillRepository.deleteAll();
        List<HomunculusSkill> homunculusSkills = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/homunculus-skills/";
        RestTemplate restTemplate = new RestTemplate();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                HomunculusSkill homunculusSkill = new HomunculusSkill();
                homunculusSkill.setId(element.get("id").intValue());
                int skillId = element.get("skill").intValue();
                Optional<Skill> skill = skillRepository.findById(skillId);
                if (skill.isPresent()) {
                    homunculusSkill.setSkill(skill.get());
                } else {
                    log.warn("Skill doesn't find: {}", skillId);
                }

                Iterable<JsonNode> prerequisitesNodes = () -> element.get("prerequisites").elements();
                List<Integer> prerequisitesIds = StreamSupport.stream(prerequisitesNodes.spliterator(), false)
                        .map(JsonNode::intValue)
                        .collect(Collectors.toList());

                List<Skill> prerequisites = skillRepository.findAllById(prerequisitesIds);
                if (prerequisitesIds.size() != prerequisites.size()) {
                    log.warn("The size of prerequisites aren't similar: {}, ids: {}", prerequisites.size(), prerequisitesIds.size());
                }

                homunculusSkill.setPrerequisites(prerequisites);
                homunculusSkills.add(homunculusSkill);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        homunculusSkillRepository.saveAll(homunculusSkills);
    }
}

package com.example.demo.component;

import com.example.demo.model.HomunculusSkill;
import com.example.demo.model.LeaderSkill;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import com.example.demo.repository.HomunculusSkillRepository;
import com.example.demo.repository.LeaderSkillRepository;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.repository.SkillRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonsterLoader {
    private final MonsterRepository monsterRepository;
    private final LeaderSkillRepository leaderSkillRepository;
    private final SkillRepository skillRepository;
    private final HomunculusSkillRepository homunculusSkillRepository;

    public void loadAll() {
        List<Monster> monsters = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/monsters/";
        RestTemplate restTemplate = new RestTemplate();
        Map<Integer, Monster> monstersById = new HashMap<>();
        Map<Integer, Integer> awakenFromByMonster = new HashMap<>();
        Map<Integer, Integer> awakenToByMonster = new HashMap<>();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                Monster monster = new Monster();
                monster.setId(element.get("id").intValue());
                monster.setName(element.get("name").textValue());
                monster.setFamilyId(element.get("family_id").intValue());
                monster.setImageFilename(element.get("image_filename").textValue());
                monster.setElement(element.get("element").textValue());
                monster.setType(element.get("archetype").textValue());
                monster.setBaseStars(element.get("base_stars").intValue());
                monster.setAwakenLevel(element.get("awaken_level").intValue());
                monster.setAwakenBonus(element.get("awaken_bonus").textValue());
                monster.setSpeed(element.get("speed").intValue());
                monster.setMaxHp(element.get("max_lvl_hp").intValue());
                monster.setMaxAttack(element.get("max_lvl_attack").intValue());
                monster.setMaxDefense(element.get("max_lvl_defense").intValue());
                monster.setCriticalRate(element.get("crit_rate").intValue());
                monster.setCriticalDamage(element.get("crit_damage").intValue());
                monster.setResistance(element.get("resistance").intValue());
                monster.setAccuracy(element.get("accuracy").intValue());
                monster.setIsFusionFood(element.get("fusion_food").booleanValue());
                monster.setIsHomuncul(element.get("homunculus").booleanValue());

                Iterable <JsonNode> sources = () -> element.get("source").elements();
                if (StreamSupport.stream(sources.spliterator(), false).count() > 0) {
                    monster.setSource(true);
                } else {
                    monster.setSource(false);
                }

                awakenFromByMonster.put(monster.getId(), element.get("awakens_from").intValue());
                awakenToByMonster.put(monster.getId(), element.get("awakens_to").intValue());
                monster.setSkillUpsCount(element.get("skill_ups_to_max").intValue());

                if (!element.get("leader_skill").isNull()) {
                    int leaderSkillId = element.get("leader_skill").get("id").intValue();
                    Optional<LeaderSkill> leaderSkill = leaderSkillRepository.findById(leaderSkillId);
                    if (!leaderSkill.isPresent()) {
                        log.warn("No such leader skill: {}", leaderSkillId);
                    } else {
                        monster.setLeaderSkill(leaderSkill.get());
                    }
                }

                Iterable<JsonNode> skillNodes = () -> element.get("skills").elements();
                List<Integer> skillIds = StreamSupport.stream(skillNodes.spliterator(), false)
                        .map(JsonNode::intValue)
                        .collect(Collectors.toList());

                List<Skill> skills = skillRepository.findAllById(skillIds);
                if (skillIds.size() != skills.size()) {
                    log.warn("The size of skill's aren't similar: {}, ids: {}", skills.size(), skillIds.size());
                }
                monster.setSkills(skills);

                Iterable<JsonNode> homunculusSkillsNodes = () -> element.get("homunculus_skills").elements();
                List<Integer> homunculusSkillsIds = StreamSupport.stream(homunculusSkillsNodes.spliterator(), false)
                        .map(JsonNode::intValue)
                        .collect(Collectors.toList());

                List<HomunculusSkill> homunculusSkills = homunculusSkillRepository.findAllById(homunculusSkillsIds);
                if (homunculusSkillsIds.size() != homunculusSkills.size()) {
                    log.warn("The size of homunculus skill's aren't similar: {}, ids: {}", homunculusSkills.size(), homunculusSkillsIds.size());
                }

                monster.setHomunculusSkills(homunculusSkills);

                monstersById.put(monster.getId(), monster);
                monsters.add(monster);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        monsterRepository.saveAll(monsters);

        awakenFromByMonster.forEach((monsterId, awakenFrom) -> {
            monstersById.get(monsterId).setAwakensFrom(awakenFrom != null ? monstersById.get(awakenFrom) : null);
        });

        awakenToByMonster.forEach((monsterId, awakenTo) -> {
            monstersById.get(monsterId).setAwakensTo(awakenTo != null ? monstersById.get(awakenTo) : null);
        });

        monsterRepository.saveAll(monsters);
        log.info("Loaded monsters");
    }

    public void preload() {
        monsterRepository.deleteAll();
    }
}
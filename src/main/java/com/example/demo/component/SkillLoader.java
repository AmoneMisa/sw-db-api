package com.example.demo.component;

import com.example.demo.model.Effect;
import com.example.demo.model.Monster;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillEffect;
import com.example.demo.repository.EffectRepository;
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
public class SkillLoader {
    private final SkillRepository skillRepository;
    private final EffectRepository effectRepository;

    public void loadAll() {
        List<Skill> skills = new ArrayList<>();
        String next = "https://swarfarm.com/api/v2/skills/";
        RestTemplate restTemplate = new RestTemplate();

        do {
            log.info(next);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(next, JsonNode.class);
            Iterable<JsonNode> elements = () -> response.getBody().get("results").elements();

            for (JsonNode element : elements) {
                Skill skill = new Skill();
                skill.setId(element.get("id").intValue());
                skill.setName(element.get("name").textValue());
                skill.setDescription(element.get("description").textValue());
                skill.setSlot(element.get("slot").intValue());
                skill.setCooltime(element.get("cooltime").intValue());
                skill.setHits(element.get("hits").intValue());
                skill.setPassive(element.get("passive").booleanValue());
                skill.setAoe(element.get("aoe").booleanValue());
                skill.setMaxLevel(element.get("max_level").intValue());

                Iterable<JsonNode> effectNodes = () -> element.get("effects").elements();
                List<SkillEffect> effects = new ArrayList<>();
                for (JsonNode effectNode : effectNodes) {
                    SkillEffect skillEffect = new SkillEffect();
                    int effectId = effectNode.get("effect").get("id").intValue();
                    Optional<Effect> effect = effectRepository.findById(effectId);

                    if (!effect.isPresent()) {
                        log.warn("The effect doesn't exists: {}", effectId);
                        continue;
                    }
                    skillEffect.setEffect(effect.get());

                    skillEffect.setAoe(effectNode.get("aoe").booleanValue());
                    skillEffect.setSingleTarget(effectNode.get("single_target").booleanValue());
                    skillEffect.setSelfEffect(effectNode.get("self_effect").booleanValue());
                    skillEffect.setOnCrit(effectNode.get("on_crit").booleanValue());
                    skillEffect.setOnDeath(effectNode.get("on_death").booleanValue());
                    skillEffect.setRandom(effectNode.get("random").booleanValue());
                    skillEffect.setAll(effectNode.get("all").booleanValue());
                    skillEffect.setSelfHp(effectNode.get("self_hp").booleanValue());
                    skillEffect.setTargetHp(effectNode.get("target_hp").booleanValue());
                    skillEffect.setDamage(effectNode.get("damage").booleanValue());
                    skillEffect.setChance(effectNode.get("chance").intValue());
                    skillEffect.setQuantity(effectNode.get("quantity").intValue());
                    skillEffect.setNote(effectNode.get("note").textValue());

                    effects.add(skillEffect);
                }

                skill.setEffects(effects);

                skill.setMultiplierFormula(element.get("multiplier_formula").textValue());
                skill.setMultiplierFormulaRaw(element.get("multiplier_formula_raw").textValue());

                Iterable<JsonNode> scalesWithNodes = () -> element.get("scales_with").elements();
                List<String> scalesWith = StreamSupport.stream(scalesWithNodes.spliterator(), false)
                        .map(JsonNode::textValue)
                        .collect(Collectors.toList());

                skill.setScalesWith(scalesWith);
                skill.setIconName(element.get("icon_filename").textValue());
                Iterable<JsonNode> levelProgressDescriptionNodes = () -> element.get("level_progress_description").elements();
                List<String> levelProgressDescription = StreamSupport.stream(levelProgressDescriptionNodes.spliterator(), false)
                        .map(JsonNode::textValue)
                        .collect(Collectors.toList());
                skill.setLevelProgressDescription(levelProgressDescription);

                skills.add(skill);
            }

            next = response.getBody().get("next").textValue();
        } while (next != null);

        skillRepository.saveAll(skills);
        log.info("Loaded skills");
    }

    public void preload() {
        skillRepository.deleteAll();
    }
}

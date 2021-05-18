package com.example.demo.controller;

import com.example.demo.component.MonsterLoader;
import com.example.demo.component.mapper.MonsterMapper;
import com.example.demo.dto.*;
import com.example.demo.model.Monster;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.util.MonsterSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monster")
public class MonsterController {
    private final MonsterRepository monsterRepository;
    private final MonsterLoader monsterLoader;
    private final MonsterMapper monsterMapper;

    @GetMapping
    public Page<MonsterItemDto> getAll(Pageable pageable) {
        return monsterRepository.findAll(pageable)
                .map(monsterMapper::toItemDto);
    }

    @GetMapping("/{id}")
    public MonsterDto getOne(@PathVariable int id) {
        return monsterRepository.findById(id)
                .map(monsterMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Monster with this id not found"));
    }

    @PostMapping("/search")
    public Page<MonsterItemDto> searchAll(@RequestBody RequestSearchMonsterDto request, Pageable pageable) {
        Specification<Monster> specification = Specification.where(null);
        String filterName = request.getName();

        if (filterName != null) {
            specification = specification.and(MonsterSpecifications.equalsName(filterName));
        }

        String filterType = request.getType();

        if (filterType != null) {
            specification = specification.and(MonsterSpecifications.equalsType(filterType));
        }

        String filterElement = request.getElement();

        if (filterElement != null) {
            specification = specification.and(MonsterSpecifications.equalsElement(filterElement));
        }

        Integer baseStarsLte = request.getBaseStarsLte();

        if (baseStarsLte != null) {
            specification = specification.and(MonsterSpecifications.lteBaseStars(baseStarsLte));
        }

        Integer baseStarsGte = request.getBaseStarsGte();

        if (baseStarsGte != null) {
            specification = specification.and(MonsterSpecifications.gteBaseStars(baseStarsGte));
        }

        Integer awakenLevel = request.getAwakenLevel();

        if (awakenLevel != null) {
            specification = specification.and(MonsterSpecifications.awakenLevel(awakenLevel));
        }

        Integer speedLte = request.getSpeedLte();

        if (speedLte != null) {
            specification = specification.and(MonsterSpecifications.lteSpeed(speedLte));
        }

        Integer speedGte = request.getSpeedGte();

        if (speedGte != null) {
            specification = specification.and(MonsterSpecifications.gteSpeed(speedGte));
        }

        Integer attackLte = request.getMaxAttackLte();

        if (attackLte != null) {
            specification = specification.and(MonsterSpecifications.lteAttack(attackLte));
        }

        Integer attackGte = request.getMaxAttackGte();

        if (attackGte != null) {
            specification = specification.and(MonsterSpecifications.gteAttack(attackGte));
        }

        Integer defenseLte = request.getMaxDefenseLte();

        if (defenseLte != null) {
            specification = specification.and(MonsterSpecifications.lteDefense(defenseLte));
        }

        Integer defenseGte = request.getMaxDefenseGte();

        if (defenseGte != null) {
            specification = specification.and(MonsterSpecifications.gteDefense(defenseGte));
        }

        Integer hpLte = request.getMaxHpLte();

        if (hpLte != null) {
            specification = specification.and(MonsterSpecifications.lteHp(hpLte));
        }

        Integer hpGte = request.getMaxHpGte();

        if (hpGte != null) {
            specification = specification.and(MonsterSpecifications.gteHp(hpGte));
        }

        Integer criticalRate = request.getCriticalRate();

        if (criticalRate != null) {
            specification = specification.and(MonsterSpecifications.criticalRate(criticalRate));
        }

        Integer resistance = request.getResistance();

        if (resistance != null) {
            specification = specification.and(MonsterSpecifications.resistance(resistance));
        }

        Integer accuracy = request.getAccuracy();

        if (accuracy != null) {
            specification = specification.and(MonsterSpecifications.accuracy(accuracy));
        }

       Boolean homuncul = request.getIsHomuncul();

        if (homuncul != null) {
            specification = specification.and(MonsterSpecifications.homuncul(homuncul));
        }

       Boolean fusionFood = request.getIsFusionFood();

        if (fusionFood != null) {
            specification = specification.and(MonsterSpecifications.fusionFood(fusionFood));
        }

        RequestSearchLeaderSkillDto leaderSkill = request.getLeaderSkill();

        if (leaderSkill != null) {
            specification = specification.and(MonsterSpecifications.filterByLeaderSkill(leaderSkill));
        }

        List<RequestSearchSkillDto> skills = request.getSkills();

        if (skills != null) {
            specification = specification.and(MonsterSpecifications.filterBySkills(skills));
        }

        return monsterRepository.findAll(specification, pageable)
                .map(monsterMapper::toItemDto);
    }

    @GetMapping("/load")
    public String load() {
        monsterLoader.loadAll();
        return "loaded";
    }
}

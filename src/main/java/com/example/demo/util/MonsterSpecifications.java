package com.example.demo.util;

import com.example.demo.dto.RequestSearchEffectDto;
import com.example.demo.dto.RequestSearchLeaderSkillDto;
import com.example.demo.dto.RequestSearchSkillDto;
import com.example.demo.dto.RequestSearchSkillEffectDto;
import com.example.demo.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class MonsterSpecifications {
    public static Specification<Monster> lteBaseStars(Integer baseStars) {
        return SpecificationUtil.lessThanOrEqualTo("baseStars", baseStars);
    }

    public static Specification<Monster> gteBaseStars(Integer baseStars) {
        return SpecificationUtil.greaterThanOrEqualTo("baseStars", baseStars);
    }

    public static Specification<Monster> lteSpeed(Integer speed) {
        return SpecificationUtil.lessThanOrEqualTo("speed", speed);
    }

    public static Specification<Monster> gteSpeed(Integer speed) {
        return SpecificationUtil.greaterThanOrEqualTo("speed", speed);
    }

    public static Specification<Monster> lteAttack(Integer attack) {
        return SpecificationUtil.lessThanOrEqualTo("maxAttack", attack);
    }

    public static Specification<Monster> gteAttack(Integer attack) {
        return SpecificationUtil.greaterThanOrEqualTo("maxAttack", attack);
    }

    public static Specification<Monster> lteDefense(Integer defense) {
        return SpecificationUtil.lessThanOrEqualTo("maxDefense", defense);
    }

    public static Specification<Monster> gteDefense(Integer defense) {
        return SpecificationUtil.greaterThanOrEqualTo("maxDefense", defense);
    }

    public static Specification<Monster> lteHp(Integer hp) {
        return SpecificationUtil.lessThanOrEqualTo("maxHp", hp);
    }

    public static Specification<Monster> gteHp(Integer hp) {
        return SpecificationUtil.greaterThanOrEqualTo("maxHp", hp);
    }

    public static Specification<Monster> equalsName(String name) {
        return SpecificationUtil.equals("name", name);
    }

    public static Specification<Monster> equalsElement(String element) {
        return SpecificationUtil.equals("element", element);
    }

    public static Specification<Monster> awakenLevel(Integer awakenLevel) {
        return SpecificationUtil.equals("awakenLevel", awakenLevel);
    }

    public static Specification<Monster> criticalRate(Integer criticalRate) {
        return SpecificationUtil.equals("criticalRate", criticalRate);
    }

    public static Specification<Monster> resistance(Integer resistance) {
        return SpecificationUtil.equals("resistance", resistance);
    }

    public static Specification<Monster> accuracy(Integer accuracy) {
        return SpecificationUtil.equals("accuracy", accuracy);
    }

    public static Specification<Monster> fusionFood(Boolean fusionFood) {
        return SpecificationUtil.equals("isFusionFood", fusionFood);
    }

    public static Specification<Monster> homuncul(Boolean homuncul) {
        return SpecificationUtil.equals("isHomuncul", homuncul);
    }

    public static Specification<Monster> equalsType(String type) {
        return SpecificationUtil.equals("type", type);
    }

    public static Specification<Monster> filterByLeaderSkill(RequestSearchLeaderSkillDto request) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate predicate = criteriaBuilder.conjunction();
            Join<Monster, LeaderSkill> join = root.join("leaderSkill");

            String filterAttribute = request.getAttribute();

            if (filterAttribute != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("attribute"), filterAttribute));
            }

            String filterArea = request.getArea();

            if (filterArea != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("area"), filterArea));
            }

            String filterElement = request.getElement();

            if (filterElement != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("element"), filterElement));
            }

            Integer filterAmount = request.getAmount();

            if (filterAmount != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(join.get("amount"), filterAmount));
            }

            return predicate;
        };
    }

    public static Specification<Monster> filterBySkills(List<RequestSearchSkillDto> skills) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate predicate = criteriaBuilder.conjunction();

            for (RequestSearchSkillDto skill : skills) {
                ListJoin<Monster, Skill> skillsJoin = root.joinList("skills");

                predicate = criteriaBuilder.and(predicate, buildSkillPredicate(skill, skillsJoin, criteriaBuilder));
            }

            return predicate;
        };
    }

    private static Predicate buildSkillPredicate(RequestSearchSkillDto request, ListJoin<Monster, Skill> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        List<RequestSearchSkillEffectDto> filterSkillEffects = request.getEffects();

        if (filterSkillEffects != null) {
            for (RequestSearchSkillEffectDto skillEffect : filterSkillEffects) {
                ListJoin<Skill, SkillEffect> skillEffectsJoin = root.joinList("effects");

                predicate = criteriaBuilder.and(predicate, buildSkillEffectPredicate(skillEffect, skillEffectsJoin, criteriaBuilder));
            }
        }

        return predicate;
    }

    private static Predicate buildSkillEffectPredicate(RequestSearchSkillEffectDto request, ListJoin<Skill, SkillEffect> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();


        // skill effect fields
        Boolean filterAoe = request.getAoe();

        if (filterAoe != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("aoe"), filterAoe));
        }

        Boolean filterSingleTarget = request.getSingleTarget();

        if (filterSingleTarget != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("singleTarget"), filterSingleTarget));
        }

        Boolean filterSelfEffect = request.getSelfEffect();

        if (filterSelfEffect != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("selfEffect"), filterSelfEffect));
        }

        Boolean filterOnCrit = request.getOnCrit();

        if (filterOnCrit != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("onCrit"), filterOnCrit));
        }

        Boolean filterOnDeath = request.getOnDeath();

        if (filterOnDeath != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("onDeath"), filterOnDeath));
        }

        Boolean filterRandom = request.getRandom();

        if (filterRandom != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("random"), filterRandom));
        }

        Boolean filterAll = request.getAll();

        if (filterAll != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("all"), filterAll));
        }

        Boolean filterSelfHp = request.getSelfHp();

        if (filterSelfHp != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("selfHp"), filterSelfHp));
        }

        Boolean filterTargetHp = request.getTargetHp();

        if (filterTargetHp != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("targetHp"), filterTargetHp));
        }

        Boolean filterDamage = request.getDamage();

        if (filterDamage != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("damage"), filterDamage));
        }

       Integer filterChance = request.getChance();

        if (filterChance != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("chance"), filterChance));
        }

       Integer filterQuantity = request.getQuantity();

        if (filterQuantity != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("quantity"), filterQuantity));
        }

        RequestSearchEffectDto filterEffect = request.getEffect();

        if (filterEffect != null) {
            predicate = criteriaBuilder.and(predicate, buildEffectPredicate(filterEffect, root.join("effect"), criteriaBuilder));
        }

        return predicate;
    }

    private static Predicate buildEffectPredicate(RequestSearchEffectDto request, Join<SkillEffect, Effect> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        String filterName = request.getName();

        if (filterName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), filterName));
        }

        return predicate;
    }

}

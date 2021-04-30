package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestSearchMonsterDto {
    private String name;
    private String element;
    private String type;
    private Integer baseStarsGte;
    private Integer baseStarsLte;
    private Integer awakenLevel;
    private Integer speedGte;
    private Integer speedLte;
    private Integer maxAttackGte;
    private Integer maxAttackLte;
    private Integer maxDefenseGte;
    private Integer maxDefenseLte;
    private Integer maxHpGte;
    private Integer maxHpLte;
    private Integer criticalRate;
    private Integer resistance;
    private Integer accuracy;
    private Boolean isFusionFood;
    private Boolean isHomuncul;
    private RequestSearchLeaderSkillDto leaderSkill;
    private List<RequestSearchSkillDto> skills;
}

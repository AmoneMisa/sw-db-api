package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MonsterDto {
    private Integer id;
    private String name;
    private Integer familyId;
    private String imageFilename;
    private String element;
    private String type;
    private Integer baseStars;
    private Integer awakenLevel;
    private Integer speed;
    private Integer maxAttack;
    private Integer maxDefense;
    private Integer maxHp;
    private Integer criticalRate;
    private Integer criticalDamage;
    private Integer resistance;
    private Integer accuracy;
    private Boolean isFusionFood;
    private Boolean source;
    private MonsterItemDto awakensFrom;
    private MonsterItemDto awakensTo;
    private Boolean isHomuncul;
    private Integer skillUpsCount;
    private LeaderSkillDto leaderSkill;
    private List<SkillDto> skills;
    private List<HomunculusSkillDto> homunculusSkills;
}

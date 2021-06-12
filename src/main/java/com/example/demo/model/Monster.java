package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Monster {
    @Id
    private Integer id;
    private String name;
    private Integer familyId;
    private String imageFilename;
    private String element;
    private String type;
    private Integer baseStars;
    private Integer awakenLevel;
    private String awakenBonus;
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
    @ManyToOne
    private Monster awakensFrom;
    @ManyToOne
    private Monster awakensTo;
    private Boolean isHomuncul;
    private Integer skillUpsCount;
    @ManyToOne
    private LeaderSkill leaderSkill;
    @ManyToMany
    @OrderBy("slot asc")
    private List<Skill> skills;
    @ManyToMany
    private List<HomunculusSkill> homunculusSkills;
}

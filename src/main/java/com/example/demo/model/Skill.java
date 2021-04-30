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
public class Skill {
    @Id
    private Integer id;
    private String name;
    @Column(length = 1024)
    private String description;
    private Integer slot;
    private Integer cooltime;
    private Integer hits;
    private Boolean passive;
    private Boolean aoe;
    private Integer maxLevel;
    @ElementCollection
    private List<SkillEffect> effects;
    private String multiplierFormula;
    private String multiplierFormulaRaw;
    @ElementCollection
    private List<String> scalesWith;
    private String iconName;
    @ManyToMany(mappedBy = "skills")
    private List<Monster> usedOn;
    @ElementCollection
    private List<String> levelProgressDescription;
}

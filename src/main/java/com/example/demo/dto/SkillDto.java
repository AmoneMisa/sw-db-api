package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SkillDto {
    private Integer id;
    private String name;
    private String description;
    private Integer slot;
    private Integer cooltime;
    private Integer hits;
    private Boolean passive;
    private Boolean aoe;
    private Integer maxLevel;
    private List<SkillEffectDto> effects;
    private String multiplierFormula;
    private String multiplierFormulaRaw;
    private List<String> scalesWith;
    private String iconName;
    private List<Integer> usedOn;
    private List<String> levelProgressDescription;
}

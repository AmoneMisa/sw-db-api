package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SkillEffectDto {
    private EffectDto effect;
    private Boolean aoe;
    private Boolean singleTarget;
    private Boolean selfEffect;
    private Boolean onCrit;
    private Boolean onDeath;
    private Boolean random;
    private Boolean all;
    private Boolean selfHp;
    private Boolean targetHp;
    private Boolean damage;
    private Integer chance;
    private Integer quantity;
    private String note;
}

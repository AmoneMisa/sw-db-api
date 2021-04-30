package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
@ToString
public class SkillEffect {
    @ManyToOne
    private Effect effect;
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

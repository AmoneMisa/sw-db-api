package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class HomunculusSkill {
    @Id
    private Integer id;
    @ManyToOne
    private Skill skill;
    @ManyToMany
    private List<Skill> prerequisites;
    @ManyToMany(mappedBy = "homunculusSkills")
    private List<Monster> usedOn;
}

package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HomunculusSkillDto {
    private Integer id;
    private Integer skill;
    private List<Integer> prerequisites;
    private List<Integer> usedOn;
}

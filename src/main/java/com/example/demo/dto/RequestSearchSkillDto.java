package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestSearchSkillDto {
    private List<RequestSearchSkillEffectDto> effects;
    private String name;
    private Integer slot;
    private Integer cooltime;
    private Integer hits;
    private Boolean passive;
    private Boolean aoe;
    private List<String> scalesWith;
}

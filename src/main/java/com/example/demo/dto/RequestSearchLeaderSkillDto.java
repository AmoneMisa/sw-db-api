package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestSearchLeaderSkillDto {
    private String attribute;
    private Integer amount;
    private String area;
    private String element;
}

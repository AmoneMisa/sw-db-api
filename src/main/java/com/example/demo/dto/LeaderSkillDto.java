package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LeaderSkillDto {
    private Integer id;
    private String attribute;
    private Integer amount;
    private String area;
    private String element;
}

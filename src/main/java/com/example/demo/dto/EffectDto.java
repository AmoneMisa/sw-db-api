package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EffectDto {
    private Integer id;
    private String name;
    private String type;
    private String description;
    private String iconName;
}

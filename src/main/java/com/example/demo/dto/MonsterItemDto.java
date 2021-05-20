package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonsterItemDto {
    private Integer id;
    private String name;
    private String imageFilename;
    private String element;
    private Integer baseStars;
    private Boolean source;
}

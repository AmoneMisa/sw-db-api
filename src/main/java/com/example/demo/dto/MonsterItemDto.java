package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MonsterItemDto {
    private Integer id;
    private String name;
    private String imageFilename;
    private String element;
    private Integer baseStars;
}

package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FusionDto {
    private Integer id;
    private Integer product;
    private List<Integer> ingredients;
}

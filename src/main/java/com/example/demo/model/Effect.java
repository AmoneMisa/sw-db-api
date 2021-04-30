package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Effect {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String description;
    private String iconName;
}

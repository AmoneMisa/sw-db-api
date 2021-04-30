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
public class LeaderSkill {
    @Id
    private Integer id;
    private String attribute;
    private Integer amount;
    private String area;
    private String element;
}

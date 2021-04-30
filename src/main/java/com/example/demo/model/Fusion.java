package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Fusion {
    @Id
    private Integer id;
    @ManyToOne
    private Monster product;
    @ManyToMany
    private List<Monster> ingredients;
}

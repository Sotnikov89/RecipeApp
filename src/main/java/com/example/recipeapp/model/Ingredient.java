package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    private String description;
    private int amount;
    @OneToOne (fetch = FetchType.EAGER)
    private UnitOfMeasure uOM;

}

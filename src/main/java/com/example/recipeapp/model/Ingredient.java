package com.example.recipeapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Recipe recipe;
    private String description;
    private int amount;
    @OneToOne (fetch = FetchType.EAGER)
    private UnitOfMeasure uOM;

}

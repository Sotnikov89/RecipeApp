package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String directions;
    private String source;
    private String url;
    private int prepTime;
    private int cookTime;
    private int servings;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients= new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] images;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

}

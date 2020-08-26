package com.example.recipeapp.model;

import lombok.*;

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
    @Lob
    private String directions;
    private String source;
    private String url;
    private int prepTime;
    private double cookTime;
    private int servings;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients= new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public void addIngredient(Ingredient ingredient){
        this.getIngredients().add(ingredient);
        ingredient.setRecipe(this);
    }

    public void addNotes(Notes notes){
        this.setNotes(notes);
        notes.setRecipe(this);
    }

}

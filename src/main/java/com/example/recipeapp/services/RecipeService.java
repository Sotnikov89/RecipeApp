package com.example.recipeapp.services;

import com.example.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
    Recipe saveNewRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipe);
    void deleteById(Long id);
}

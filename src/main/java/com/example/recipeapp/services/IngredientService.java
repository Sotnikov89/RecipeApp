package com.example.recipeapp.services;

import com.example.recipeapp.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredientByRecipeIdAndId(Long recipeId, Long id);
    Ingredient saveOrUpdateIngredient(Ingredient ingredient, Long recipeId);
    void deleteByRecipeIdAndId(Long recipeId, Long id);
}

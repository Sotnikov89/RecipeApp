package com.example.recipeapp.services;

import com.example.recipeapp.exceptions.NotFoundException;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getAllRecipes() {
        return new HashSet<>(recipeRepository.findAll());
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found"));
    }

    @Override
    public Recipe saveNewRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe recipe) {  // method for RestController
        Recipe updateRecipe = recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found"));
        if (recipe.getIngredients()!=null) updateRecipe.getIngredients().addAll(recipe.getIngredients());
        if (recipe.getCategories()!=null) updateRecipe.getCategories().addAll(recipe.getCategories());
        //etc impl late
        return recipeRepository.save(updateRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}

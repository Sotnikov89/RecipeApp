package com.example.recipeapp.services;

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
}

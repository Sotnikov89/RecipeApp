package com.example.recipeapp.services;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

class DefaultRecipeServiceTest {

    DefaultRecipeService defaultRecipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        defaultRecipeService = new DefaultRecipeService(recipeRepository);
    }

    @Test
    void getRecipeById(){
        Recipe recipe = Recipe.builder().id(1L).build();
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe returnedRecipe = defaultRecipeService.showById(1L);

        assertNotNull(returnedRecipe);
        assertEquals(returnedRecipe.getId(), 1L);
    }

    @Test
    void getAllRecipes() {

        List<Recipe> dataRecipe = List.of(new Recipe());

        when(recipeRepository.findAll()).thenReturn(dataRecipe);

        Set<Recipe> recipes = defaultRecipeService.getAllRecipes();

        assertEquals(recipes.size(), 1);
    }

}
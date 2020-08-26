package com.example.recipeapp.services;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DefaultIngredientServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    DefaultIngredientService defaultIngredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultIngredientService = new DefaultIngredientService(recipeRepository);
    }

    @Test
    void getIngredientByRecipeIdAndId() {
        Recipe recipe = Recipe.builder().id(1L).build();
        Ingredient ingredient = Ingredient.builder().id(2L).recipe(recipe).build();
        recipe.setIngredients(Set.of(ingredient));

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Ingredient sameIngredient = defaultIngredientService.getIngredientByRecipeIdAndId(1L,2L);

        assertEquals(sameIngredient.getId(), 2l);
        assertEquals(sameIngredient.getRecipe().getId(), 1l);

        verify(recipeRepository, times(2)).findById(anyLong());
    }

    @Test
    void saveOrUpdateIngredient() {
    }

    @Test
    void deleteByRecipeIdAndId() {
    }
}
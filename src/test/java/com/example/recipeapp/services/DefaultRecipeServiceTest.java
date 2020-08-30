package com.example.recipeapp.services;

import com.example.recipeapp.exceptions.NotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

        Recipe returnedRecipe = defaultRecipeService.getRecipeById(1L);

        assertNotNull(returnedRecipe);
        assertEquals(returnedRecipe.getId(), 1L);
    }

    @Test
    void getRecipeByIdNotFound(){

        Exception exception = assertThrows(NotFoundException.class, () ->{
            defaultRecipeService.getRecipeById(1L);
        });

        String exceptedMessage = "Recipe not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptedMessage));
    }

    @Test
    void getAllRecipes() {

        List<Recipe> recipes = List.of(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> recipeSet = defaultRecipeService.getAllRecipes();

        assertEquals(recipeSet.size(), 1);
    }

    @Test
    void saveOrUpdateRecipe() {

        Recipe recipe = Recipe.builder().id(3L).build();

        when(recipeRepository.save(any())).thenReturn(recipe);

        Recipe returnedRecipe = defaultRecipeService.saveNewRecipe(recipe);

        assertNotNull(returnedRecipe);
        assertEquals(returnedRecipe.getId(), 3L);
    }

    @Test
    void deleteById() {

        Long id = 3L;
        defaultRecipeService.deleteById(id);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
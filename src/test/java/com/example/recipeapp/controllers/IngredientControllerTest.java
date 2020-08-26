package com.example.recipeapp.controllers;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.IngredientService;
import com.example.recipeapp.services.RecipeService;
import com.example.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;
    @Mock
    RecipeService recipeService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService,ingredientService,unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void showRecipeIngredients() throws Exception {
        Recipe recipe = Recipe.builder().id(3L).build();

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/3/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).getRecipeById(anyLong());
    }

    @Test
    void addNewRecipeIngredient() throws Exception {
        Recipe recipe = Recipe.builder().id(3L).build();

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);
        when(unitOfMeasureService.getAllUnitOfMeasure()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).getRecipeById(anyLong());
        verify(unitOfMeasureService, times(1)).getAllUnitOfMeasure();
    }

    @Test
    void updateRecipeIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();

        when(ingredientService.getIngredientByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredient);
        when(unitOfMeasureService.getAllUnitOfMeasure()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void saveOrUpdateRecipeIngredient() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        ingredient.setRecipe(Recipe.builder().id(2L).build());

        when(ingredientService.saveOrUpdateIngredient(any(),anyLong())).thenReturn(ingredient);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

    }

    @Test
    void deleteIngredient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/ingredient/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteByRecipeIdAndId(anyLong(), anyLong());
    }
}
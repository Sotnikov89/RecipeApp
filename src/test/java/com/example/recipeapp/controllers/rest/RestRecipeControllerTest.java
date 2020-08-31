package com.example.recipeapp.controllers.rest;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestRecipeController.class)
class RestRecipeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RecipeService recipeService;

    @Test
    void showAllRecipes() throws Exception {
        Set<Recipe> recipeList = Set.of(
                Recipe.builder().id(1L).build(),
                Recipe.builder().id(2L).build(),
                Recipe.builder().id(3L).build(),
                Recipe.builder().id(4L).build());
        String expectedJsonResponse = objectMapper.writeValueAsString(recipeList);

        when(recipeService.getAllRecipes()).thenReturn(recipeList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recipes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String realResponseJson = mvcResult.getResponse().getContentAsString();

        assertThat(realResponseJson).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void showRecipeById() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).build();
        String expectedJsonResponse = objectMapper.writeValueAsString(recipe);

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recipes/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String realResponseJson = mvcResult.getResponse().getContentAsString();

        assertThat(realResponseJson).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void createNewRecipe() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).build();

        given(recipeService.saveNewRecipe(any())).willReturn(Recipe.builder().id(1L).build());

        mockMvc.perform(post("/api/v1/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateRecipeById()throws Exception{
        Recipe recipe = Recipe.builder().id(1L).build();

        given(recipeService.updateRecipeById(anyLong(), any())).willReturn(Recipe.builder().id(1L).build());

        mockMvc.perform(put("/api/v1/recipes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(recipe)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRecipe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/recipes/1"))
                .andExpect(status().isNoContent());
    }
}
package com.example.recipeapp.controllers.rest;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/recipes")
@AllArgsConstructor
public class RestRecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Recipe> showAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe showRecipeById(@PathVariable Long id){
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe createNewRecipe(@RequestBody Recipe recipe){
        return recipeService.saveNewRecipe(recipe);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Recipe updateRecipeById(@PathVariable Long id, @RequestBody Recipe recipe){
        return recipeService.updateRecipeById(id, recipe);
    }

    @DeleteMapping ("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe (@PathVariable Long id){
        recipeService.deleteById(id);
    }
}

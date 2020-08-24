package com.example.recipeapp.controllers;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.model.UnitOfMeasure;
import com.example.recipeapp.services.IngredientService;
import com.example.recipeapp.services.RecipeService;
import com.example.recipeapp.services.UnitOfMeasureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOMeasureService;

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String showRecipeIngredients(@PathVariable Long recipeId, Model model){

        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String addNewRecipeIngredient(@PathVariable Long recipeId, Model model){

        Recipe existRecipe = recipeService.getRecipeById(recipeId);
        Ingredient ingredient = Ingredient.builder().recipe(existRecipe).uOM(new UnitOfMeasure()).build();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uomList", unitOMeasureService.getAllUnitOfMeasure());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){

        model.addAttribute("uomList", unitOMeasureService.getAllUnitOfMeasure());
        model.addAttribute("ingredient", ingredientService.getIngredientByRecipeIdAndId(recipeId, ingredientId));

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateRecipeIngredient(@PathVariable Long recipeId, @ModelAttribute Ingredient ingredient){

        Ingredient savedIngredient = ingredientService.saveOrUpdateIngredient(ingredient, recipeId);

        return "redirect:/recipe/"+savedIngredient.getRecipe().getId()+"/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId){

        ingredientService.deleteByRecipeIdAndId(recipeId, ingredientId);

        return "redirect:/recipe/"+recipeId+"/ingredients";
    }
}

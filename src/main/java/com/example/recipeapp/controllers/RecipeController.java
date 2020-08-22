package com.example.recipeapp.controllers;

import com.example.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping("/recipe/show/{id}")
    public String getRecipeById(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.showById(id));
        return "recipe/show";
    }

}

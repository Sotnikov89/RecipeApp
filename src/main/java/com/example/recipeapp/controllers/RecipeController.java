package com.example.recipeapp.controllers;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.getRecipeById(id));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String addNewRecipe(Model model){

        model.addAttribute("recipe", new Recipe());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    private String updateRecipe(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.getRecipeById(id));

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute Recipe recipe){

        Recipe savedRecipe = recipeService.saveOrUpdateRecipe(recipe);

        return "redirect:/recipe/"+savedRecipe.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable Long id){

        recipeService.deleteById(id);

        return "redirect:index";
    }
}

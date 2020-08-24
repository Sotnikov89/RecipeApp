package com.example.recipeapp.services;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultIngredientService implements IngredientService {

    private final RecipeRepository recipeRepository;

    @Override
    public Ingredient getIngredientByRecipeIdAndId(Long recipeId, Long id) {
        if (recipeRepository.findById(recipeId).isPresent())
            return recipeRepository.findById(recipeId).get().getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(id)).findFirst()
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        else throw new RuntimeException("Recipe not found");
    }

    @Override
    public Ingredient saveOrUpdateIngredient(Ingredient ingredient, Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalRecipe.isPresent()) {

            Recipe recipe = optionalRecipe.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().
                    filter(value -> value.getId().equals(ingredient.getId())).findFirst();

            if (ingredientOptional.isPresent()){
                //update existing ingredient
                Ingredient existIngredient = ingredientOptional.get();
                existIngredient.setUOM(ingredient.getUOM());
                existIngredient.setAmount(ingredient.getAmount());
                existIngredient.setDescription(ingredient.getDescription());

                Recipe savedRecipe = recipeRepository.save(recipe);

                return savedRecipe.getIngredients().stream().
                        filter(value -> value.getId().equals(ingredient.getId())).findFirst()
                        .orElseThrow(() -> new RuntimeException("Ingredient not found"));

            } else {
                //add new ingredient
                recipe.getIngredients().add(ingredient);
                ingredient.setRecipe(recipe);
                Recipe savedRecipe = recipeRepository.save(recipe);

                Optional<Ingredient> newIngredient = savedRecipe.getIngredients().stream()
                        .filter(value -> value.getDescription().equals(ingredient.getDescription()))
                        .filter(value -> value.getAmount()==(ingredient.getAmount()))
                        .filter(value -> value.getUOM().getId().equals(ingredient.getUOM().getId()))
                        .findFirst();

                return newIngredient.orElseThrow(() -> new RuntimeException("Ingredient not found"));
            }
        } else throw new RuntimeException("Recipe not found");
    }

    @Override
    public void deleteByRecipeIdAndId(Long recipeId, Long id) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new RuntimeException("Recipe not found"));
        recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(id)).findFirst()
                .ifPresent(ingredient -> ingredient.setRecipe(null));
        recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(id));
        recipeRepository.save(recipe);
    }
}

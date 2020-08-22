package com.example.recipeapp.bootstrap;

import com.example.recipeapp.model.*;
import com.example.recipeapp.repositories.CategoryRepository;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class BootstrapRecipe implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> listRecipes = new ArrayList<>();

        Recipe borshRecipe = new Recipe();

        borshRecipe.setCategories(Set.of(categoryRepository.findByCategoryName("Украинская")));
        borshRecipe.setPrepTime(10);
        borshRecipe.setCookTime(2.5);
        borshRecipe.setDescription("Борщ");
        borshRecipe.setDifficulty(Difficulty.MODERATE);
        borshRecipe.setSource("Сайт рецептов Анастасии Скрипкиной");
        borshRecipe.setUrl("https://www.say7.info/cook/recipe/259-Borsch.html");
        borshRecipe.setServings(8);

        borshRecipe.setDirections("Мясо залить водой и варить в течение 1.5 часа. Затем достать, нарезать небольшими" +
                " кусочками и добавить в бульон. "+
                "Лук мелко нарезать. Морковь натереть на средней терке. Капусту нашинковать тонкой соломкой. "+
                "Свеклу нарезать тонкой соломкой и обжарить на растительном масле. Добавить уксус и томатную пасту (если паста густая, добавить немного воды). " +
                "Тушить в течение 5–7 минут. "+
                "На растительном масле обжарить лук. Добавить морковь. "+
                "Картофель нарезать кубиками или брусочками и добавить в кипяший бульон. Посолить. "+
                "Когда бульон закипит, добавить капусту. Варить на небольшом огне в течение 5 минут. "+
                "Добавить свеклу и варить еще 10 минут. Затем добавить лук и морковь. "+
                "Добавить лавровый лист. Если необходимо, посолить, поперчить. Добавить чеснок, выдавленный через чеснокодавку. " +
                "Убрать с огня. Дать настояться в течение 15–20 минут. " +
                "Борщ готов!");

        borshRecipe.addIngredient(Ingredient.builder().description("говядина (мякоть или на косточке)").amount(1).uOM(unitOfMeasureRepository.findByUomName("кг.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("картофель").amount(500).uOM(unitOfMeasureRepository.findByUomName("г.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("свежая капуста").amount(300).uOM(unitOfMeasureRepository.findByUomName("г.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("свекла").amount(400).uOM(unitOfMeasureRepository.findByUomName("г.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("морковь").amount(200).uOM(unitOfMeasureRepository.findByUomName("г.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("лук").amount(200).uOM(unitOfMeasureRepository.findByUomName("г.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("томатная паста").amount(3).uOM(unitOfMeasureRepository.findByUomName("ст. л.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("уксус 6%").amount(1).uOM(unitOfMeasureRepository.findByUomName("ч. л.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("зубчики чеснока").amount(2).uOM(unitOfMeasureRepository.findByUomName("шт.")).build());
        borshRecipe.addIngredient(Ingredient.builder().description("лавровый лист").amount(3).uOM(unitOfMeasureRepository.findByUomName("шт.")).build());

        borshRecipe.addNotes(Notes.builder().recipeNotes("Традиционный рецепт допускает сочетание до 20 ингредиентов, главные из которых " +
                "- свекла, морковь, томаты, лук, чеснок, картошка (к ним могут прибавляться фасоль, капуста, грибы, потроха и так далее). " +
                "Основа борща - наваристый бульон - говяжий, свиной или овощной. " +
                "В конце приготовления в блюдо добавляется \"затирка\" - перетертое сало с чесноком и перцем, дающая божественный аромат. " +
                "Несомненная польза борща - содержащиеся в нем овощи, которые не только богаты клетчаткой " +
                "(она обладает свойством сорбента - мягко выводит из организма токсины), но и сохраняют полезные свойства в вареном виде. " +
                "В свекле много витаминов группы В, РР, йода, калия, железа. В вареной моркови содержится в три раза больше антиоксидантов, чем в сырой - эти вещества защищают от рака."
        ).build());

        listRecipes.add(borshRecipe);

        return listRecipes;
    }
}

package com.example.recipeapp.services;

import com.example.recipeapp.exceptions.NotFoundException;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DefaultImageService implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    public void saveImage(Long recipeId, MultipartFile multipartFile) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new NotFoundException("Recipe not found"));

        try {
            Byte[] bytes = new Byte[multipartFile.getBytes().length];
            int i = 0;
            for (byte b : multipartFile.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

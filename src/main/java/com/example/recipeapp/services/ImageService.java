package com.example.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void saveImage(Long recipeId, MultipartFile multipartFile);
}

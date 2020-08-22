package com.example.recipeapp.model;

public enum Difficulty {

    EASY("Лёгкая"),
    MODERATE("Средняя"),
    HARD("Сложная");

    private String title;

    Difficulty(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

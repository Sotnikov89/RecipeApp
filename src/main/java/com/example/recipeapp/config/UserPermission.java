package com.example.recipeapp.config;

public enum UserPermission {

    UPDATE_RECIPE("recipe:update"),
    DELETE_RECIPE("recipe:delete");

    private final String permission;

    UserPermission(String s) {
        this.permission=s;
    }

    public String getPermission() {
        return permission;
    }
}

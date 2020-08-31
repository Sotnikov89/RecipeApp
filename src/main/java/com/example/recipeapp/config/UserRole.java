package com.example.recipeapp.config;

import java.util.Set;

public enum UserRole {
    ADMIN(Set.of(UserPermission.values()));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}

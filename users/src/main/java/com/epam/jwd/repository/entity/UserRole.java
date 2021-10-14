package com.epam.jwd.repository.entity;

import java.util.Arrays;

public enum UserRole {
    USER(1),
    ADMIN(2);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole getById(int id) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

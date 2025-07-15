package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ROLE_ADMIN,
    ROLE_CLIENT;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null) return null;
        return switch (value.toUpperCase()) {
            case "ADMIN", "ROLE_ADMIN" -> ROLE_ADMIN;
            case "CLIENT", "ROLE_CLIENT" -> ROLE_CLIENT;
            default -> throw new IllegalArgumentException("Invalid role: " + value);
        };
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}

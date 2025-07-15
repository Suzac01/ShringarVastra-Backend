package com.example.demo.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) return null;
        return switch (role) {
            case ROLE_ADMIN -> "admin";
            case ROLE_CLIENT -> "client";
        };
    }

    @Override
    public Role convertToEntityAttribute(String dbValue) {
        return Role.fromString(dbValue);
    }
}




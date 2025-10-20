package com.example.demo.dto.auth;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role; // Optional: Only if roles are manually selected during registration
}

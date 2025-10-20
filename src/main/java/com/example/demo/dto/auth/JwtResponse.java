package com.example.demo.dto.auth;

import com.example.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Role role;
    private String name;
}

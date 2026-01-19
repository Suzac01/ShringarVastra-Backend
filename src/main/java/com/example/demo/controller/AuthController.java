package com.example.demo.controller;

import com.example.demo.dto.auth.JwtResponse;
import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.RegisterRequest;
import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.admin.JwtUtil;
import com.example.demo.service.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        try {
//            User newUser = new User();
//            String fullName = request.getFirstName() + " " + request.getLastName();
//            newUser.setName(fullName);
//            newUser.setEmail(request.getEmail());
//            newUser.setPassword(request.getPassword());
//            newUser.setRole(Role.ROLE_CLIENT);
//
//            User savedUser = authService.saveUser(newUser);
//            return ResponseEntity.ok(savedUser);
//        }
//        catch (DataIntegrityViolationException e) {
//            // This happens when email already exists (unique constraint fails)
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body("Email already exists");
//        }
//        catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Something went wrong");
//        }
//    }
//
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//
//        // 🔹 Log incoming request
//        System.out.println("Login attempt:");
//        System.out.println("Email: " + request.getEmail());
//        System.out.println("Password: " + request.getPassword());
//
//        User user = authService.login(request.getEmail(), request.getPassword());
//
//        if (user == null) {
//            System.out.println("Login failed: invalid credentials for " + request.getEmail());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail());
//
//        JwtResponse response = new JwtResponse(token, user.getRole(), user.getName());
//
//        System.out.println("Login successful for user: " + user.getEmail());
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        User user = authService.login(request.getEmail(), request.getPassword());
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//        return ResponseEntity.ok(user);
//    }

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        User newUser = new User();
//        newUser.setName(request.getFirstName() + " " + request.getLastName());
//        newUser.setEmail(request.getEmail());
//        newUser.setPassword(request.getPassword());
//        newUser.setRole(Role.ROLE_CLIENT);
//
//        User savedUser = authService.saveUser(newUser);
//        return ResponseEntity.ok(savedUser);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User newUser = new User();
        newUser.setName(request.getFirstName() + " " + request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setRole(Role.ROLE_CLIENT);

        User savedUser = authService.saveUser(newUser);
        return ResponseEntity.ok(savedUser);
    }

    // AuthController.java (add below existing endpoints)
    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Google google) {
        User user = authService.loginOrRegister(google); // use instance, not class

        String token = jwtUtil.generateToken(user.getEmail());
        JwtResponse response = new JwtResponse(token, user.getRole(), user.getName());

        return ResponseEntity.ok(response);
    }
}








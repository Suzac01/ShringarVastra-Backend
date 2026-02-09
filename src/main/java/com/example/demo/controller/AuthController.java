package com.example.demo.controller;

import com.example.demo.dto.auth.JwtResponse;
import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.RegisterRequest;
import com.example.demo.dto.auth.changePasswordRequest.ChangePasswordRequest;
import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.dto.auth.resetPassword.EmailRequest;
import com.example.demo.dto.auth.resetPasswordRequest.ResetPasswordRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.admin.JwtUtil;
import com.example.demo.service.authentication.AuthService;
import com.example.demo.service.emailService.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.ok(user);
    }

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

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody EmailRequest emailRequest) {
        try {
            // Generate reset link
            String resetLink = authService.generateResetPasswordToken(emailRequest.getEmail());

            // Send email
            emailService.sendPasswordResetEmail(emailRequest.getEmail(), resetLink);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Password reset link has been sent to your email");
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "error");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        boolean isValid = authService.validateResetToken(token);

        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            response.put("valid", true);
            response.put("message", "Token is valid");
            return ResponseEntity.ok(response);
        } else {
            response.put("valid", false);
            response.put("message", "Invalid or expired token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetRequest) {
        try {
            // Validate passwords match
            if (!resetRequest.getNewPassword().equals(resetRequest.getConfirmPassword())) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Passwords do not match");
                errorResponse.put("status", "error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Validate password strength (optional)
            if (resetRequest.getNewPassword().length() < 6) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Password must be at least 6 characters long");
                errorResponse.put("status", "error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Reset password
            authService.resetPassword(resetRequest.getToken(), resetRequest.getNewPassword());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Password has been reset successfully");
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "error");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String authHeader,
                                            @Valid @RequestBody ChangePasswordRequest changeRequest) {
        try {
            // Extract email from JWT token
            String token = authHeader.replace("Bearer ", "");
            String email = jwtUtil.extractUsername(token);

            if (email == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            // Get user
            User user = authService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Verify current password
            if (!passwordEncoder.matches(changeRequest.getCurrentPassword(), user.getPassword())) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Current password is incorrect");
                errorResponse.put("status", "error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Validate new password
            if (!changeRequest.getNewPassword().equals(changeRequest.getConfirmPassword())) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "New passwords do not match");
                errorResponse.put("status", "error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Reset password using the same method
            String encodedPassword = passwordEncoder.encode(changeRequest.getNewPassword());
            user.setPassword(encodedPassword);
            user.setPasswordLastChanged(LocalDateTime.now());
            authService.updateUser(user);

            // Send confirmation email
            emailService.sendPasswordChangedConfirmation(user.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Password changed successfully");
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to change password");
            errorResponse.put("status", "error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}








package com.example.demo.service.authentication;

import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class AuthServiceImpl implements AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Use BCrypt for hashing
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @Override
//    public User saveUser(User user) {
//        System.out.println("Saving user: " + user);
//
//        // 🔹 Hash the password before saving
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User login(String username, String password) {
//        // 🔹 Find the user by email
//        User user = userRepository.findByEmail(username).orElse(null);
//
//        if (user == null) {
//            System.out.println("User not found for email: " + username);
//            return null;
//        }
//
//        // 🔹 Compare raw password with hashed password
//        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
//
//        if (!isPasswordMatch) {
//            System.out.println("Password mismatch for user: " + username);
//            return null;
//        }
//
//        return user;
//    }
//
//    @Override
//    public User loginOrRegister(Google google) {
//        // Find if the user already exists
//        User user = userRepository.findByEmail(google.getEmail()).orElse(null);
//
//        if (user == null) {
//            // New Google user → save details
//            user = new User();
//            user.setName(google.getName());
//            user.setEmail(google.getEmail());
//            user.setAppwriteId(google.getAppwriteId()); // Or google.getSub()
//            user.setRole(Role.ROLE_CLIENT);
//            user.setProvider("GOOGLE");
//            userRepository.save(user);
//
//            System.out.println("✅ New Google user saved: " + google.getEmail());
//        } else {
//            System.out.println("🔁 Existing Google user logged in: " + google.getEmail());
//        }
//
//        return user;
//    }
//}
import com.example.demo.service.emailService.EmailService;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    // Use BCrypt for hashing
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User saveUser(User user) {
        System.out.println("Saving user: " + user);

        // 🔹 Hash the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setPasswordLastChanged(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        // 🔹 Find the user by email
        User user = userRepository.findByEmail(username).orElse(null);

        if (user == null) {
            System.out.println("User not found for email: " + username);
            return null;
        }

        // Check if user is a Google user (no password)
        if ("GOOGLE".equals(user.getProvider())) {
            System.out.println("Google user trying to use password login: " + username);
            return null;
        }

        // 🔹 Compare raw password with hashed password
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

        if (!isPasswordMatch) {
            System.out.println("Password mismatch for user: " + username);
            return null;
        }

        return user;
    }

    @Override
    public User loginOrRegister(Google google) {
        // Find if the user already exists
        User user = userRepository.findByEmail(google.getEmail()).orElse(null);

        if (user == null) {
            // New Google user → save details
            user = new User();
            user.setName(google.getName());
            user.setEmail(google.getEmail());
            user.setAppwriteId(google.getAppwriteId()); // Or google.getSub()
            user.setRole(Role.ROLE_CLIENT);
            user.setProvider("GOOGLE");
            userRepository.save(user);

            System.out.println("✅ New Google user saved: " + google.getEmail());
        } else {
            System.out.println("🔁 Existing Google user logged in: " + google.getEmail());
        }

        return user;
    }

    @Override
    public String generateResetPasswordToken(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // Check if user is a Google user
        if ("GOOGLE".equals(user.getProvider())) {
            throw new RuntimeException("Google users cannot reset password. Please use Google login.");
        }

        // Generate unique token
        String token = UUID.randomUUID().toString();

        // Set token and expiry (30 minutes from now)
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiry(LocalDateTime.now().plusMinutes(30));

        userRepository.save(user);

        // Return reset link
        return frontendUrl + "/reset-password?token=" + token;
    }

    @Override
    public boolean validateResetToken(String token) {
        User user = userRepository.findByResetPasswordToken(token).orElse(null);

        if (user == null) {
            return false;
        }

        // Check if token is expired
        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now())) {
            // Clear expired token
            user.setResetPasswordToken(null);
            user.setResetPasswordTokenExpiry(null);
            userRepository.save(user);
            return false;
        }

        return true;
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token).orElse(null);

        if (user == null) {
            throw new RuntimeException("Invalid or expired reset token");
        }

        // Validate token expiry
        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now())) {
            // Clear expired token
            user.setResetPasswordToken(null);
            user.setResetPasswordTokenExpiry(null);
            userRepository.save(user);
            throw new RuntimeException("Reset token has expired");
        }

        // Hash new password
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setPasswordLastChanged(LocalDateTime.now());

        // Clear reset token
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiry(null);

        userRepository.save(user);

        // Send confirmation email
        emailService.sendPasswordChangedConfirmation(user.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByResetToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElse(null);
    }
}

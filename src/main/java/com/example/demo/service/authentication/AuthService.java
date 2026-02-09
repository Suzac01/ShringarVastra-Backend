package com.example.demo.service.authentication;

import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.model.User;

public interface AuthService {

    User saveUser(User user);

    User login(String username, String password);

    User loginOrRegister(Google google);

    String generateResetPasswordToken(String email);

    boolean validateResetToken(String token);

    void resetPassword(String token, String newPassword);

    User findByEmail(String email);

    User findByResetToken(String token);

    User updateUser(User user);
}

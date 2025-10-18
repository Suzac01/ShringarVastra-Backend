package com.example.demo.service.authentication;

import com.example.demo.dto.auth.googleRegister.Google;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        System.out.println("Saving user: " + user);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password);
    }

    @Override
    public User loginOrRegister(Google google) {
        User user = userRepository.findByEmail(google.getEmail()).orElse(null);

        if (user == null) {
            user = new User();
            user.setName(google.getName());
            user.setEmail(google.getEmail());
            user.setAppwriteId(google.getAppwriteId());
            user.setRole(Role.ROLE_CLIENT);
            user = userRepository.save(user);
        }

        return user;
    }

}
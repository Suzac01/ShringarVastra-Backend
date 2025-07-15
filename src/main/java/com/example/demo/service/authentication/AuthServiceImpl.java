package com.example.demo.service.authentication;

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

//    @Override
//    public User login(String username, String password) {
//        // Example login method, replace with actual query and security logic
//        return userRepository.findByEmailAndPassword(username, password);
//    }
}
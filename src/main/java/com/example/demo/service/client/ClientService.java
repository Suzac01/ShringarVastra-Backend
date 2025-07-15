package com.example.demo.service.client;

import com.example.demo.model.Products;
import com.example.demo.model.User;

import java.util.List;

public interface ClientService {
    List<User> getAllUsers();

    User saveUser(User user);
}

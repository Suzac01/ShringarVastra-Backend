package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService userService;

    @GetMapping("/getUsers")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        System.out.println("User role received: " + user.getRole());
        return userService.saveUser(user);
    }
}

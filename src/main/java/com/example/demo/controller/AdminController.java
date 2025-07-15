package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addProduct")
    public Products addProduct(@RequestBody Products product) {
        return adminService.saveProduct(product);
    }
}

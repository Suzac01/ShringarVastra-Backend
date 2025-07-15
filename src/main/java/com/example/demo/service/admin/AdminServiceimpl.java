package com.example.demo.service.admin;

import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }
}

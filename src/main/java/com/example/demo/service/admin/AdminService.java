package com.example.demo.service.admin;

import com.example.demo.model.Products;

import java.util.List;

public interface AdminService {


    Products saveProduct(Products product); // Create a product.

    List<Products> getAllProducts(); // Return a list of product.

    Products updateProduct(Products product); // Update one product.

    void deleteProduct(Long id); // Delete by product ID.

}

package com.example.demo.service.admin;

import com.example.demo.dto.customer.CustomerDTO;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Products;
import com.example.demo.model.User;

import java.util.List;

public interface AdminService {


    Products saveProduct(ProductRequest request); // Create a product.

    List<Products> getAllProducts(); // Return a list of product.

    Products updateProduct(Products product); // Update one product.

    void deleteProduct(Long id); // Delete by product ID.

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO dto);

//    void softDeleteCustomer(Long id);

    void hardDeleteCustomer(Long id);

    CustomerDTO getCustomerById(Long id);


}

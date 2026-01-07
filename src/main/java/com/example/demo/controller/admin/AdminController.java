package com.example.demo.controller.admin;

import com.example.demo.dto.customer.CustomerDTO;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.service.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//add edit update delete product from admin.
@RestController
@RequestMapping("/admin/product")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // CREATE - Add a new product using DTO
//    @PostMapping("/add")
//    public ResponseEntity<Products> addProduct(@Valid @RequestBody ProductRequest request) {
//        Products product = new Products();
//        product.setProductName(request.getName());
//        product.setProductPrice(request.getPrice());
//
//        Products savedProduct = adminService.saveProduct(product);
//        return ResponseEntity.ok(savedProduct);
//    }

    // CREATE - Add a new product

    @PostMapping("/add")
    public ResponseEntity<Products> addProduct(@Valid @RequestBody ProductRequest request) {
        Products savedProduct = adminService.saveProduct(request);
        return ResponseEntity.ok(savedProduct);
    }

    // READ - Get all products
    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = adminService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // UPDATE - Update an existing product
    @PutMapping("/update")
    public ResponseEntity<Products> updateProduct(@RequestBody ProductRequest request) {
        System.out.println("Received update payload: " + request);
        Products product = new Products();
        product.setId(request.getId()); // important!
        product.setProductName(request.getName());
        product.setProductPrice(request.getPrice());
        Products updatedProduct = adminService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE - Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // GET -All customer api by Role
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = adminService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/customersEdit/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO dto) {

        System.out.println("Updating customer ID: " + id);
        System.out.println("Payload: " + dto);


        CustomerDTO updatedCustomer = adminService.updateCustomer(id, dto);
        return ResponseEntity.ok(updatedCustomer);
    }


    @DeleteMapping("/customersDelete/{id}")
    public ResponseEntity<String> hardDeleteCustomer(@PathVariable Long id) {

        adminService.hardDeleteCustomer(id);
        return ResponseEntity.ok("Customer deleted permanently");
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {

        CustomerDTO customer = adminService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

}

package com.example.demo.controller.admin;

import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Products;
import com.example.demo.service.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // CREATE - Add a new product using DTO
    @PostMapping("/add")
    public ResponseEntity<Products> addProduct(@Valid @RequestBody ProductRequest request) {
        Products product = new Products();
        product.setProductName(request.getName());
        product.setProductPrice(request.getPrice());

        Products savedProduct = adminService.saveProduct(product);
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
    public ResponseEntity<Products> updateProduct(@RequestBody Products product) {
        Products updatedProduct = adminService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE - Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}

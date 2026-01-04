//package com.example.demo.service.admin;
//
//import com.example.demo.dto.CategoryRequest.CategoryRequest;
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.model.Products;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface AdminService {
//
//
//    Products saveProduct(ProductRequest request); // Create a product.
//
//    List<Products> getAllProducts(); // Return a list of product.
//
//    Products updateProduct(Products product); // Update one product.
//
//    void deleteProduct(Long id); // Delete by product ID.
//
//    Categories saveCategory(CategoryRequest request, MultipartFile imageFile) throws IOException;
//
//    List<Categories> getAllCategories();
//
//    Categories updateCategory(Categories category, MultipartFile imageFile) throws IOException;
//
//    void deleteCategory(Long id);
//}


package com.example.demo.service.admin;

import com.example.demo.dto.CategoryRequest.CategoryRequest;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.model.Categories;
import com.example.demo.model.Products;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    // Product CRUD operations
    ProductResponse saveProduct(ProductRequest request);
    List<Products> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);

    // Product search and filter
    List<ProductResponse> searchProducts(String name, String sku, String brand, String category);
    List<ProductResponse> getProductsByStatus(String status);

    // Image handling
    ProductResponse uploadProductImages(Long productId, List<MultipartFile> imageFiles) throws IOException;
    ProductResponse deleteProductImage(Long productId, String imageUrl);

    // Category operations
    Categories saveCategory(CategoryRequest request) throws IOException;
    List<Categories> getAllCategories();
    Categories getCategoryById(Long id);
    Categories updateCategory(Long id, CategoryRequest request, MultipartFile imageFile) throws IOException;
    void deleteCategory(Long id);
}
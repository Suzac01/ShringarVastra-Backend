//package com.example.demo.service.admin;
//
//import com.example.demo.dto.CategoryRequest.CategoryRequest;
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.model.Products;
//import com.example.demo.repository.categoryRepository.CategoryRepository;
//import com.example.demo.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class AdminServiceimpl implements AdminService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//
//    @Override
//    public Products saveProduct(ProductRequest request) {
//        Products product = new Products();
//        mapProductDtoToEntity(request, product);
//        return productRepository.save(product);
//    }
//
//    @Override
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Products updateProduct(Products product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//
//
//    private void mapProductDtoToEntity(ProductRequest request, Products product) {
//        product.setProductName(request.getName());
//        product.setProductPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        product.setWeight(request.getWeight());
//        product.setStockQuantity(request.getStockQuantity());
//        product.setRating(request.getRating());
//        product.setReviewsCount(request.getReviewsCount());
//        product.setSku(request.getSku());
//        product.setStatus(request.getStatus());
//        product.setBrand(request.getBrand());
//        product.setDiscount(request.getDiscount());
//        product.setTags(request.getTags());
//
//        if (request.getCategoryId() != null) {
//            Categories category = categoryRepository.findById(request.getCategoryId().longValue())
//                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
//            product.setCategory(category);
//        }
//    }
//
//    // ===== Category Methods =====
//    public Categories saveCategory(CategoryRequest request, MultipartFile imageFile) throws IOException {
//        Categories category = new Categories();
//        category.setCategoryName(request.getCategoryName());
//        category.setDescription(request.getDescription());
//
//        // Handle image file
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String uploadDir = "uploads/categories/";
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            File dest = new File(uploadDir + fileName);
//            dest.getParentFile().mkdirs();
//            imageFile.transferTo(dest);
//            category.setImage(uploadDir + fileName); // save path or file name
//        }
//
//        return categoryRepository.save(category);
//    }
//
//    public List<Categories> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    public Categories updateCategory(Categories category, MultipartFile imageFile) throws IOException {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String uploadDir = "uploads/categories/";
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            File dest = new File(uploadDir + fileName);
//            dest.getParentFile().mkdirs();
//            imageFile.transferTo(dest);
//            category.setImage(uploadDir + fileName);
//        }
//        return categoryRepository.save(category);
//    }
//
//    public void deleteCategory(Long id) {
//        categoryRepository.deleteById(id);
//    }
//}


package com.example.demo.service.admin;

import com.example.demo.dto.CategoryRequest.CategoryRequest;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Categories;
import com.example.demo.model.Products;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdminServiceimpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${app.upload.dir}")
    private String uploadDir; // configurable folder from application.properties


    @Override
    public Products saveProduct(ProductRequest request) {
        Products product = new Products();
        mapProductDtoToEntity(request, product);
        return productRepository.save(product);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products updateProduct(Products product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private void mapProductDtoToEntity(ProductRequest request, Products product) {
        product.setProductName(request.getName());
        product.setProductPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setWeight(request.getWeight());
        product.setStockQuantity(request.getStockQuantity());
        product.setRating(request.getRating());
        product.setReviewsCount(request.getReviewsCount());
        product.setSku(request.getSku());
        product.setStatus(request.getStatus());
        product.setBrand(request.getBrand());
        product.setDiscount(request.getDiscount());
        product.setTags(request.getTags());

        if (request.getCategoryId() != null) {
            Categories category = categoryRepository.findById(request.getCategoryId().longValue())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
            product.setCategory(category);
        }
    }

    // ===== Category Methods =====
    @Override
    public Categories saveCategory(CategoryRequest request, MultipartFile imageFile) throws IOException {
        Categories category = new Categories();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        if (imageFile != null && !imageFile.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // create folder if missing
            }

            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            imageFile.transferTo(filePath.toFile()); // save file
            category.setImage(fileName);
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories updateCategory(Categories category, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            imageFile.transferTo(filePath.toFile());
            category.setImage(fileName);
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

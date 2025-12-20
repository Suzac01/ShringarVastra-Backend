//package com.example.demo.service.admin;
//
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.model.Products;
//import com.example.demo.model.User;
//import com.example.demo.repository.categoryRepository.CategoryRepository;
//import com.example.demo.repository.ProductRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.categoryRepository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class AdminServiceimpl implements AdminService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    private CategoryRepository categoryRepository;
//
//
////    @Override
////    public Products saveProduct(Products product) {
////        return productRepository.save(product);
////    }
//
//
//    @Override
//    public Products saveProduct(ProductRequest request) {
//        Products product = new Products();
//
//        mapDtoToEntity(request, product);
//
//        return productRepository.save(product);
//    }
//
//    @Override
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//
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
//    private void mapDtoToEntity(ProductRequest request, Products product) {
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
//}
//


package com.example.demo.service.admin;

import com.example.demo.dto.CategoryRequest.CategoryRequest;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Categories;
import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AdminServiceimpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Products saveProduct(ProductRequest request) {
        Products product = new Products();
        mapDtoToEntity(request, product);
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

    @Override
    public Categories saveCategory(CategoryRequest request) {
        Categories category = new Categories();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        return categoryRepository.save(category);
    }

    private void mapDtoToEntity(ProductRequest request, Products product) {
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
}

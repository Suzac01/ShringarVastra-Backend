package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    boolean existsBySku(String sku);

    List<Products> findByProductNameContainingIgnoreCase(String productName);

    List<Products> findBySkuContainingIgnoreCase(String sku);

    List<Products> findByBrandContainingIgnoreCase(String brand);

    List<Products> findByCategoryCategoryNameContainingIgnoreCase(String categoryName);

    List<Products> findByStatus(String status);

    List<Products> findByCategoryId(Long categoryId);
}
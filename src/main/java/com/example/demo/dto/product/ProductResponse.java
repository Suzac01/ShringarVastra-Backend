package com.example.demo.dto.product;

import com.example.demo.model.Categories;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String sku;
    private Double productPrice;
    private String brand;
    private String status;
    private String description;
    private Categories category;
    private List<String> tags;
    private List<String> productImage;
    private Integer stockQuantity;
    private Double weight;
    private String dimensions;
    private Double rating;
    private Integer reviewsCount;
    private Double discount;
    private Boolean isFeatured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
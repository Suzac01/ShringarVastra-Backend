package com.example.demo.dto.product;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class ProductRequest {

    // From frontend: name, sku, price, brand, status, description
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU cannot exceed 100 characters")
    private String sku;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Size(max = 255, message = "Brand cannot exceed 255 characters")
    private String brand;

    @NotBlank(message = "Status is required")
    private String status; // ACTIVE, INACTIVE, DRAFT, OUT_OF_STOCK

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    // From frontend: category (but in DB it's category_id)
    @NotNull(message = "Category ID is required")
    private Long categoryId;

    // From frontend: tags, images
    private List<String> tags;

    @NotNull(message = "At least one image is required")
    @Size(min = 1, message = "At least one image is required")
    private List<String> images;

    // Optional fields
    private Integer stockQuantity;
    private Double weight;
    private Double rating;
    private Integer reviewsCount;
    private Double discount;
    private String dimensions;
    private Boolean isFeatured;
}
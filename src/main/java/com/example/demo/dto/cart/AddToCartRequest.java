package com.example.demo.dto.cart;


import lombok.Getter;

@Getter
public class AddToCartRequest {
    // Getters and setters
    private Long userId;
    private Long productId;
    private int quantity;
    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

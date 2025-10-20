package com.example.demo.dto.cart;

public class CartItemResponse {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private Long userId;

    public CartItemResponse(Long id, String productName, int quantity, double price, Long userId) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

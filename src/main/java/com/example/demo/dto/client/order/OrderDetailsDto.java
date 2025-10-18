package com.example.demo.dto.client.order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailsDto {
    private Long orderId;
    private String status;
    private LocalDateTime orderDate;
    private List<ItemDetail> items;

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public List<ItemDetail> getItems() {
        return items;
    }
    public void setItems(List<ItemDetail> items) {
        this.items = items;
    }

    public static class ItemDetail {
        private String productName;
        private double price;
        private int quantity;

        // Getters and Setters
        public String getProductName() {
            return productName;
        }
        public void setProductName(String productName) {
            this.productName = productName;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}

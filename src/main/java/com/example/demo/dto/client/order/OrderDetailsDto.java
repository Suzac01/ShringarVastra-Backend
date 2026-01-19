//package com.example.demo.dto.client.order;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class OrderDetailsDto {
//    private Long orderId;
//    private String status;
//    private LocalDateTime orderDate;
//    private List<ItemDetail> items;
//
//    // Getters and Setters
//    public Long getOrderId() {
//        return orderId;
//    }
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//    public List<ItemDetail> getItems() {
//        return items;
//    }
//    public void setItems(List<ItemDetail> items) {
//        this.items = items;
//    }
//
//    public static class ItemDetail {
//        private String productName;
//        private double price;
//        private int quantity;
//
//        // Getters and Setters
//        public String getProductName() {
//            return productName;
//        }
//        public void setProductName(String productName) {
//            this.productName = productName;
//        }
//        public double getPrice() {
//            return price;
//        }
//        public void setPrice(double price) {
//            this.price = price;
//        }
//        public int getQuantity() {
//            return quantity;
//        }
//        public void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//    }
//}
//
//package com.example.demo.dto.client.order;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class OrderDetailsDto {
//    private Long orderId;
//    private LocalDateTime orderDate;
//    private String status;
//    private String shippingAddress;
//    private String paymentMethod;
//    private String additionalNote;
//    private String clientEmail;
//    private Double totalAmount;
//    private List<OrderItemDetailDto> items;
//
//    // Inner class for order items
//    public static class OrderItemDetailDto {
//        private Long productId;
//        private String productName;
//        private Integer quantity;
//        private Double unitPrice;
//        private Double totalPrice;
//
//        // Getters and Setters
//        public Long getProductId() { return productId; }
//        public void setProductId(Long productId) { this.productId = productId; }
//
//        public String getProductName() { return productName; }
//        public void setProductName(String productName) { this.productName = productName; }
//
//        public Integer getQuantity() { return quantity; }
//        public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//        public Double getUnitPrice() { return unitPrice; }
//        public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
//
//        public Double getTotalPrice() { return totalPrice; }
//        public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
//    }
//
//    // Getters and Setters for main class
//    public Long getOrderId() { return orderId; }
//    public void setOrderId(Long orderId) { this.orderId = orderId; }
//
//    public LocalDateTime getOrderDate() { return orderDate; }
//    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public String getShippingAddress() { return shippingAddress; }
//    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
//
//    public String getPaymentMethod() { return paymentMethod; }
//    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
//
//    public String getAdditionalNote() { return additionalNote; }
//    public void setAdditionalNote(String additionalNote) { this.additionalNote = additionalNote; }
//
//    public String getClientEmail() { return clientEmail; }
//    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
//
//    public Double getTotalAmount() { return totalAmount; }
//    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
//
//    public List<OrderItemDetailDto> getItems() { return items; }
//    public void setItems(List<OrderItemDetailDto> items) { this.items = items; }
//}


package com.example.demo.dto.client.order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailsDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private String shippingAddress;
    private String paymentMethod;
    private String additionalNote;
    private String clientEmail;
    private Double totalAmount;
    private List<ItemDetail> items;  // Changed from OrderItemDetailDto to ItemDetail

    // ✅ Fix the inner class name to match what you're using
    public static class ItemDetail {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double price;
        private Double totalPrice;

        // Getters and Setters
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }

        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }

        public Double getTotalPrice() { return totalPrice; }
        public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    }

    // Getters and Setters for main class
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getAdditionalNote() { return additionalNote; }
    public void setAdditionalNote(String additionalNote) { this.additionalNote = additionalNote; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public List<ItemDetail> getItems() { return items; }
    public void setItems(List<ItemDetail> items) { this.items = items; }
}
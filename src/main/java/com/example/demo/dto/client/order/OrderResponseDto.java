//package com.example.demo.dto.client.order;
//
//import java.time.LocalDateTime;
//
//public class OrderResponseDto {
//    private Long orderId;
//    private String status;           // added
//    private LocalDateTime orderDate; // added
//    private String productName;
//    private int quantity;
//    private LocalDateTime orderedAt;
//    private double totalPrice;
//
//    public Long getOrderId() {
//        return orderId;
//    }
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public LocalDateTime getOrderedAt() {
//        return orderedAt;
//    }
//    public void setOrderedAt(LocalDateTime orderedAt) {
//        this.orderedAt = orderedAt;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//}
//
//package com.example.demo.dto.client.order;
//
//import java.time.LocalDateTime;
//
//
//// OrderResponseDto.java
//public class OrderResponseDto {
//    private Long orderId;           // Add this
//    private String status;          // Add this
//    private LocalDateTime orderDate; // Add this
//    private String shippingAddress; // Add this
//    private String paymentMethod;   // Add this
//    private String clientEmail;     // Add this
//    private Long userId;            // Add this
//    private String productName;
//    private Integer quantity;
//    private LocalDateTime orderedAt;
//    private Double totalAmount;     // Add this
//    private Double totalPrice;
//
//    // Add getters and setters for new fields
//    public Long getOrderId() { return orderId; }
//    public void setOrderId(Long orderId) { this.orderId = orderId; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public LocalDateTime getOrderDate() { return orderDate; }
//    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
//
//    public String getShippingAddress() { return shippingAddress; }
//    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
//
//    public String getPaymentMethod() { return paymentMethod; }
//    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
//
//    public String getClientEmail() { return clientEmail; }
//    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
//
//    public Long getUserId() { return userId; }
//    public void setUserId(Long userId) { this.userId = userId; }
//
//    public Double getTotalAmount() { return totalAmount; }
//    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
//
//    // Existing getters/setters
//    public String getProductName() { return productName; }
//    public void setProductName(String productName) { this.productName = productName; }
//
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//    public LocalDateTime getOrderedAt() { return orderedAt; }
//    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }
//
//    public Double getTotalPrice() { return totalPrice; }
//    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
//}

//package com.example.demo.dto.client.order;
//
//import java.time.LocalDateTime;
//
//public class OrderResponseDto {
//    private Long orderId;
//    private String status;
//    private LocalDateTime orderDate;
//    private String shippingAddress;
//    private String paymentMethod;
//    private String clientEmail;
//    private Long userId;
//    private String productName;
//    private Integer quantity;
//    private LocalDateTime orderedAt;
//    private Double totalAmount;
//    private Double totalPrice;
//
//    // Getters and Setters
//    public Long getOrderId() { return orderId; }
//    public void setOrderId(Long orderId) { this.orderId = orderId; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public LocalDateTime getOrderDate() { return orderDate; }
//    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
//
//    public String getShippingAddress() { return shippingAddress; }
//    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
//
//    public String getPaymentMethod() { return paymentMethod; }
//    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
//
//    public String getClientEmail() { return clientEmail; }
//    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
//
//    public Long getUserId() { return userId; }
//    public void setUserId(Long userId) { this.userId = userId; }
//
//    public String getProductName() { return productName; }
//    public void setProductName(String productName) { this.productName = productName; }
//
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//    public LocalDateTime getOrderedAt() { return orderedAt; }
//    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }
//
//    public Double getTotalAmount() { return totalAmount; }
//    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
//
//    public Double getTotalPrice() { return totalPrice; }
//    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
//}

package com.example.demo.dto.client.order;

import java.time.LocalDateTime;

public class OrderResponseDto {
    private Long orderId;
    private String status;
    private LocalDateTime orderDate;
    private String shippingAddress;  // Add this
    private String paymentMethod;    // Add this
    private String clientEmail;      // Add this
    private String productName;
    private Integer quantity;
    private LocalDateTime orderedAt;
    private Double totalPrice;
    private Double totalAmount;      // Add this if needed

    // Getters and Setters for all fields
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}
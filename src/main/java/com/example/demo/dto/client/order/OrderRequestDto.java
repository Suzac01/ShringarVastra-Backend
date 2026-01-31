//package com.example.demo.dto.client.order;
//
//import java.util.List;
//
//public class OrderRequestDto {
//    private String shippingAddress;
//    private String paymentMethod;
//    private String additionalNote;
//    private List<OrderItemDto> orderItems;
//
//    // Getters and Setters
//    public String getShippingAddress() {
//        return shippingAddress;
//    }
//
//    public void setShippingAddress(String shippingAddress) {
//        this.shippingAddress = shippingAddress;
//    }
//
//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public String getAdditionalNote() {
//        return additionalNote;
//    }
//
//    public void setAdditionalNote(String additionalNote) {
//        this.additionalNote = additionalNote;
//    }
//
//    public List<OrderItemDto> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItemDto> orderItems) {
//        this.orderItems = orderItems;
//    }
//
//    // Inner class for individual order items
//    public static class OrderItemDto {
//        private String productName;
//        private double price;
//        private int quantity;
//
//        // Getters and Setters
//        public String getProductName() {
//            return productName;
//        }
//
//        public void setProductName(String productName) {
//            this.productName = productName;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//
//        public void setPrice(double price) {
//            this.price = price;
//        }
//
//        public int getQuantity() {
//            return quantity;
//        }
//
//        public void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//    }
//}


package com.example.demo.dto.client.order;

import java.util.List;

public class OrderRequestDto {
    private String clientEmail;
    private String shippingAddress;
    private String paymentMethod;
    private String additionalNote;
    private List<OrderItemDto> items;  // Changed from orderItems to items

    // Getters and Setters
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getAdditionalNote() { return additionalNote; }
    public void setAdditionalNote(String additionalNote) { this.additionalNote = additionalNote; }

    public List<OrderItemDto> getItems() { return items; }  // Changed getter
    public void setItems(List<OrderItemDto> items) { this.items = items; }  // Changed setter

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}

class OrderItemDto {
    private Long productId;    // Changed from productName
    private Integer quantity;  // Changed from int to Integer

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
//package com.example.demo.model.order;
//
//import com.example.demo.model.User;
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "orders")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String clientEmail;
//
//    private String user;
//
//    private LocalDateTime orderDate;
//
//    private String status;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderItem> items;
//
//    private String shippingAddress;
//
//    private String paymentMethod;
//
//    private String additionalNote;
//
//    private String setCreatedAt;
//
//    public String getSetCreatedAt() {
//        return setCreatedAt;
//    }
//
//    public void setSetCreatedAt(String setCreatedAt) {
//        this.setCreatedAt = setCreatedAt;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
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
//    public String getShippingAddress() {
//        return shippingAddress;
//    }
//
//    public void setShippingAddress(String shippingAddress) {
//        this.shippingAddress = shippingAddress;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getClientEmail() {
//        return clientEmail;
//    }
//
//    public void setClientEmail(String clientEmail) {
//        this.clientEmail = clientEmail;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public List<OrderItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<OrderItem> items) {
//        this.items = items;
//    }
//
//    public void setCreatedAt(LocalDateTime now) {
//
//    }
//}

package com.example.demo.model.order;

import com.example.demo.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientEmail;

    @Column(name = "\"user\"") // <-- Quote the column name
    private String user;

    private LocalDateTime orderDate;

    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    private String shippingAddress;

    private String paymentMethod;

    private String additionalNote;

    private String setCreatedAt;

    // Getters and setters
    public String getSetCreatedAt() {
        return setCreatedAt;
    }

    public void setSetCreatedAt(String setCreatedAt) {
        this.setCreatedAt = setCreatedAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setCreatedAt(LocalDateTime now) {
        this.setCreatedAt = now.toString();
    }
}

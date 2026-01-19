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
//
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
//    @Column(name = "\"user\"") // <-- Quote the column name
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
//    // Getters and setters
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
//        this.setCreatedAt = now.toString();
//    }
//}

//package com.example.demo.model.order;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "orders")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "user_id")
//    private Long userId;  // Add this field
//
//    @Column(name = "client_email")
//    private String clientEmail;  // Add this field
//
//    @Column(name = "order_date")
//    private LocalDateTime orderDate;  // Add this field
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;  // Make sure this exists
//
//    private String status;
//    private String shippingAddress;
//    private String paymentMethod;
//
//    @Column(name = "additional_note")
//    private String additionalNote;
//
//    @Column(name = "user", columnDefinition = "TEXT")  // This stores user object as string
//    private String user;
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Long getUserId() { return userId; }  // Add this
//    public void setUserId(Long userId) { this.userId = userId; }  // Add this
//
//    public String getClientEmail() { return clientEmail; }  // Add this
//    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }  // Add this
//
//    public LocalDateTime getOrderDate() { return orderDate; }  // Add this
//    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }  // Add this
//
//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
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
//    public String getUser() { return user; }
//    public void setUser(String user) { this.user = user; }
//}


//
//package com.example.demo.model.order;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
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
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "client_email")
//    private String clientEmail;
//
//    @Column(name = "order_date")
//    private LocalDateTime orderDate;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    private String status;
//    private String shippingAddress;
//    private String paymentMethod;
//
//    @Column(name = "additional_note")
//    private String additionalNote;
//
//    @Column(name = "user", columnDefinition = "TEXT")
//    private String user;
//
//    // ✅ Add this relationship
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderItem> items = new ArrayList<>();
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Long getUserId() { return userId; }
//    public void setUserId(Long userId) { this.userId = userId; }
//
//    public String getClientEmail() { return clientEmail; }
//    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
//
//    public LocalDateTime getOrderDate() { return orderDate; }
//    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
//
//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
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
//    public String getUser() { return user; }
//    public void setUser(String user) { this.user = user; }
//
//    // ✅ Add getter and setter for items
//    public List<OrderItem> getItems() { return items; }
//    public void setItems(List<OrderItem> items) { this.items = items; }
//
//    // ✅ Helper method to add item
//    public void addItem(OrderItem item) {
//        items.add(item);
//        item.setOrder(this);
//    }
//}

package com.example.demo.model.order;

import com.example.demo.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to User entity instead of storing "user" as string
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // PostgreSQL-safe
    private User user;

    @Column(name = "client_email", nullable = false)
    private String clientEmail;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String status;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "additional_note")
    private String additionalNote;

    // Relation to order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();

    // =======================
    // Getters and Setters
    // =======================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getAdditionalNote() { return additionalNote; }
    public void setAdditionalNote(String additionalNote) { this.additionalNote = additionalNote; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // Helper to add items
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}

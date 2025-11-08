package com.example.demo.model.cartItem;

import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.model.order.Order;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    private int quantity;

    private String productName;

    private String imageUrl;

    private double price;

    @Column(name = "ordered")
    private boolean ordered = false;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    // Constructors
    public CartItem() {}

    public CartItem(User user, Products product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.productName = product.getProductName();
        this.imageUrl = product.getProductImage();
        try {
            this.price = product.getProductPrice();
        } catch (NullPointerException e) {
            this.price = 0.0; // default fallback price
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    // Optional setter if you want to associate Order with CartItem
    public void setOrder(Order order) {
        // Implement if needed, else can remove
    }
}

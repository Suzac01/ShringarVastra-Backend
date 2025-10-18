package com.example.demo.service.client.cart;

import com.example.demo.model.cartItem.CartItem;

import java.util.List;

public interface CartService {

    void addToCart(String userEmail, Long productId, int quantity);

    List<CartItem> getUserCart(Long userId);

    void removeItem(Long cartItemId);

    void clearCart(Long userId);
}

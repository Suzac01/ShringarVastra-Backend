package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserAndOrderedFalse(User user);

    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
    List<CartItem> findByUserEmailAndOrderedTrue(String email);


    List<CartItem> findByOrderedTrue(); // For admin to fetch all orders

    List<CartItem> findByUserEmailAndOrderedFalse(String clientEmail);
}

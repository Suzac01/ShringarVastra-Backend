package com.example.demo.repository.order;

import com.example.demo.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientEmail(String email);
    Optional<Order> findByIdAndClientEmail(Long id, String clientEmail);
}

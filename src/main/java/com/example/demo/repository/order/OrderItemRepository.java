//package com.example.demo.repository.order;
//
//import com.example.demo.model.order.Order;
//import com.example.demo.model.order.OrderItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    List<OrderItem> findByOrderId(Long orderId);
//
//    List<OrderItem> findByOrder(Order order);
//
//    List<Order> findByClientEmail(String clientEmail);
//
//    Optional<Order> findByIdAndClientEmail(Long id, String clientEmail);
//
//    List<Order> findByUserId(Long userId);
//
//}


package com.example.demo.repository.order;

import com.example.demo.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//
//    // Remove or fix this method - OrderItem doesn't have clientEmail
//    // Optional<OrderItem> findByIdAndClientEmail(Long id, String clientEmail);
//
//    // Instead, use these methods:
//    List<OrderItem> findByOrderId(Long orderId);
//
//    List<OrderItem> findByOrderIdAndOrder_ClientEmail(Long orderId, String clientEmail);
//
//    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.order.clientEmail = :clientEmail")
//    Optional<OrderItem> findByOrderIdAndClientEmail(@Param("orderId") Long orderId, @Param("clientEmail") String clientEmail);
//
//    List<OrderItem> findByProductId(Long productId);
//}

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // CORRECT: Find by order ID
    List<OrderItem> findByOrderId(Long orderId);

    // CORRECT: Find by order (if you have the relationship)
    List<OrderItem> findByOrder(com.example.demo.model.order.Order order);

    // Remove the problematic method
    // Optional<OrderItem> findByIdAndClientEmail(Long id, String clientEmail);
}
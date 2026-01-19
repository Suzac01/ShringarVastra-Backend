//package com.example.demo.repository;
//
//import com.example.demo.model.User;
//import com.example.demo.model.cartItem.CartItem;
//import com.example.demo.model.order.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//
//public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//
////    List<CartItem> findByUserAndOrderedFalse(User user);
////
////    List<CartItem> findByUser(User user);
////
////    void deleteByUser(User user);
////
////    List<CartItem> findByUserEmailAndOrderedTrue(String email);
////
////    List<CartItem> findByOrderedTrue(); // For admin to fetch all orders
////
////    List<CartItem> findByUserEmailAndOrderedFalse(String clientEmail);
//
//
//
//    List<CartItem> findByUserEmailAndOrderedFalse(String email);
//
//    List<CartItem> findByUserEmailAndOrderedTrue(String email);
//
//    List<CartItem> findByOrderId(Long orderId);
//
//    List<CartItem> findByOrder(Order order);
//
//}

package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // ✅ UNCOMMENT and FIX these methods:

    // Method 1: Find cart items by user where ordered = false
    List<CartItem> findByUserAndOrderedFalse(User user);

    // Method 2: Find all cart items by user
    List<CartItem> findByUser(User user);

    // Method 3: Delete cart items by user
    void deleteByUser(User user);

    // Method 4: Find by user email where ordered = true
    @Query("SELECT ci FROM CartItem ci WHERE ci.user.email = :email AND ci.ordered = true")
    List<CartItem> findByUserEmailAndOrderedTrue(@Param("email") String email);

    // Method 5: Find all ordered items (for admin)
    List<CartItem> findByOrderedTrue();

    // Method 6: Find by user email where ordered = false
    @Query("SELECT ci FROM CartItem ci WHERE ci.user.email = :email AND ci.ordered = false")
    List<CartItem> findByUserEmailAndOrderedFalse(@Param("email") String email);

    // Method 7: Find by order ID
    @Query("SELECT ci FROM CartItem ci WHERE ci.order.id = :orderId")
    List<CartItem> findByOrderId(@Param("orderId") Long orderId);

    // Method 8: Find by order
    List<CartItem> findByOrder(Order order);
}

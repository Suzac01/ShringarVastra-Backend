package com.example.demo.service.admin.order;

import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.model.order.Order;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.order.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
        User user = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUserEmailAndOrderedFalse(clientEmail);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("No items in cart to place an order");
        }

        Order order = new Order();
        order.setUser(String.valueOf(user));
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setAdditionalNote(orderRequest.getAdditionalNote());

        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            item.setOrdered(true);
            item.setOrderedAt(LocalDateTime.now());
            item.setOrder(order);
        }

        cartItemRepository.saveAll(cartItems);
    }


    @Override
    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
        List<CartItem> orderedItems = cartItemRepository.findByUserEmailAndOrderedTrue(clientEmail);

        return orderedItems.stream().map(item -> {
            OrderResponseDto dto = new OrderResponseDto();
            dto.setProductName(item.getProductName());  // safer than calling getProduct().getProduct_name()
            dto.setQuantity(item.getQuantity());

            dto.setTotalPrice(item.getQuantity() * item.getPrice());

            dto.setOrderedAt(item.getOrderedAt());
            return dto;
        }).toList();
    }

    @Override
    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId, String clientEmail) {
    }


}

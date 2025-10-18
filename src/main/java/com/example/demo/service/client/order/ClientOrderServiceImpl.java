package com.example.demo.service.client.order;

import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.repository.order.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
        User user = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUserAndOrderedFalse(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        // Create new Order
        Order order = new Order();
        order.setClientEmail(clientEmail);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        order.setShippingAddress(orderRequest.getShippingAddress());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());  // Correct getter here
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            // Mark cart item as ordered
            cartItem.setOrdered(true);
            cartItem.setOrderedAt(LocalDateTime.now());
        }


        order.setItems(orderItems);

        // Save Order and update cart items
        orderRepository.save(order);
        cartItemRepository.saveAll(cartItems);
    }

    @Override
    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
        List<Order> orders = orderRepository.findByClientEmail(clientEmail);
        return orders.stream().map(order -> {
            OrderResponseDto dto = new OrderResponseDto();
            dto.setOrderId(order.getId());
            dto.setStatus(order.getStatus());
            dto.setOrderDate(order.getOrderDate());

            if (!order.getItems().isEmpty()) {
                OrderItem firstItem = order.getItems().get(0);
                dto.setProductName(firstItem.getProductName());
                dto.setQuantity(firstItem.getQuantity());
                dto.setTotalPrice(firstItem.getQuantity() * firstItem.getPrice());
                dto.setOrderedAt(order.getOrderDate());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
                .orElseThrow(() -> new RuntimeException("Order not found or unauthorized"));

        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setOrderId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());

        dto.setItems(order.getItems().stream().map(item -> {
            OrderDetailsDto.ItemDetail detail = new OrderDetailsDto.ItemDetail();
            detail.setProductName(item.getProductName());
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            return detail;
        }).collect(Collectors.toList()));

        return dto;
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId, String clientEmail) {
        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
                .orElseThrow(() -> new RuntimeException("Order not found or unauthorized"));

        if (!"PENDING".equalsIgnoreCase(order.getStatus())) {
            throw new RuntimeException("Only PENDING orders can be canceled");
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}

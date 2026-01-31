//package com.example.demo.service.client.order;
//
//import com.example.demo.dto.client.order.OrderRequestDto;
//import com.example.demo.dto.client.order.OrderResponseDto;
//import com.example.demo.dto.client.order.OrderDetailsDto;
//import com.example.demo.model.User;
//import com.example.demo.model.cartItem.CartItem;
//import com.example.demo.model.order.Order;
//import com.example.demo.model.order.OrderItem;
//import com.example.demo.repository.CartItemRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.order.OrderRepository;
//import com.example.demo.repository.order.OrderItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jakarta.transaction.Transactional;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ClientOrderServiceImpl implements ClientOrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
//        User user = userRepository.findByEmail(clientEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<CartItem> cartItems = cartItemRepository.findByUserAndOrderedFalse(user);
//        if (cartItems.isEmpty()) {
//            throw new RuntimeException("Cart is empty.");
//        }
//
//        // Create new Order
//        Order order = new Order();
//        order.setClientEmail(clientEmail);
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus("PLACED");
//        order.setShippingAddress(orderRequest.getShippingAddress());
//
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for (CartItem cartItem : cartItems) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProductId(cartItem.getProduct().getId());  // Correct getter here
//            orderItem.setProductName(cartItem.getProductName());
//            orderItem.setPrice(cartItem.getPrice());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setOrder(order);
//            orderItems.add(orderItem);
//
//            // Mark cart item as ordered
//            cartItem.setOrdered(true);
//            cartItem.setOrderedAt(LocalDateTime.now());
//        }
//
//
//        order.setItems(orderItems);
//
//        // Save Order and update cart items
//        orderRepository.save(order);
//        cartItemRepository.saveAll(cartItems);
//    }
//
//    @Override
//    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
//        List<Order> orders = orderRepository.findByClientEmail(clientEmail);
//        return orders.stream().map(order -> {
//            OrderResponseDto dto = new OrderResponseDto();
//            dto.setOrderId(order.getId());
//            dto.setStatus(order.getStatus());
//            dto.setOrderDate(order.getOrderDate());
//
//            if (!order.getItems().isEmpty()) {
//                OrderItem firstItem = order.getItems().get(0);
//                dto.setProductName(firstItem.getProductName());
//                dto.setQuantity(firstItem.getQuantity());
//                dto.setTotalPrice(firstItem.getQuantity() * firstItem.getPrice());
//                dto.setOrderedAt(order.getOrderDate());
//            }
//            return dto;
//        }).collect(Collectors.toList());
//    }
//
//    @Override
//    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
//        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
//                .orElseThrow(() -> new RuntimeException("Order not found or unauthorized"));
//
//        OrderDetailsDto dto = new OrderDetailsDto();
//        dto.setOrderId(order.getId());
//        dto.setStatus(order.getStatus());
//        dto.setOrderDate(order.getOrderDate());
//
//        dto.setItems(order.getItems().stream().map(item -> {
//            OrderDetailsDto.ItemDetail detail = new OrderDetailsDto.ItemDetail();
//            detail.setProductName(item.getProductName());
//            detail.setPrice(item.getPrice());
//            detail.setQuantity(item.getQuantity());
//            return detail;
//        }).collect(Collectors.toList()));
//
//        return dto;
//    }
//
//    @Override
//    @Transactional
//    public void cancelOrder(Long orderId, String clientEmail) {
//        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
//                .orElseThrow(() -> new RuntimeException("Order not found or unauthorized"));
//
//        if (!"PENDING".equalsIgnoreCase(order.getStatus())) {
//            throw new RuntimeException("Only PENDING orders can be canceled");
//        }
//
//        order.setStatus("CANCELLED");
//        orderRepository.save(order);
//    }
//}


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
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setAdditionalNote(orderRequest.getAdditionalNote());
        order.setUser(user);

        // Save order first to get ID
        order = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItem.setCreatedAt(LocalDateTime.now());

            orderItems.add(orderItem);

            // Mark cart item as ordered
            cartItem.setOrdered(true);
            cartItem.setOrderedAt(LocalDateTime.now());
            cartItem.setOrder(order);
        }

        // Save order items
        orderItemRepository.saveAll(orderItems);

        // Update order with items (optional - for bi-directional relationship)
        order.setItems(orderItems);

        // Update cart items
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
            dto.setShippingAddress(order.getShippingAddress());
            dto.setPaymentMethod(order.getPaymentMethod());
            dto.setClientEmail(order.getClientEmail());

            // Calculate total and get first item
            double total = 0.0;
            String productName = null;
            Integer quantity = 0;

            if (!order.getItems().isEmpty()) {
                for (OrderItem item : order.getItems()) {
                    total += item.getPrice() * item.getQuantity();
                }
                OrderItem firstItem = order.getItems().get(0);
                productName = firstItem.getProductName();
                quantity = firstItem.getQuantity();
            }

            dto.setProductName(productName);
            dto.setQuantity(quantity);
            dto.setTotalPrice(total);
            dto.setOrderedAt(order.getOrderDate());

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
        dto.setShippingAddress(order.getShippingAddress());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setAdditionalNote(order.getAdditionalNote());
        dto.setClientEmail(order.getClientEmail());

        // Calculate total
        double total = 0.0;
        List<OrderDetailsDto.ItemDetail> itemDetails = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            OrderDetailsDto.ItemDetail detail = new OrderDetailsDto.ItemDetail();
            detail.setProductId(item.getProductId());
            detail.setProductName(item.getProductName());
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());

            double itemTotal = item.getPrice() * item.getQuantity();
            detail.setTotalPrice(itemTotal);

            itemDetails.add(detail);
            total += itemTotal;
        }

        dto.setItems(itemDetails);
        dto.setTotalAmount(total);

        return dto;
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId, String clientEmail) {
        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
                .orElseThrow(() -> new RuntimeException("Order not found or unauthorized"));

        if (!"PENDING".equalsIgnoreCase(order.getStatus()) && !"PLACED".equalsIgnoreCase(order.getStatus())) {
            throw new RuntimeException("Only PENDING or PLACED orders can be canceled");
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        // Optional: Mark cart items as not ordered
        List<CartItem> cartItems = cartItemRepository.findByOrder(order);
        for (CartItem item : cartItems) {
            item.setOrdered(false);
            item.setOrder(null);
            item.setOrderedAt(null);
        }
        cartItemRepository.saveAll(cartItems);
    }
}
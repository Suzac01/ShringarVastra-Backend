//package com.example.demo.service.admin.order;
//
//import com.example.demo.dto.client.order.OrderDetailsDto;
//import com.example.demo.dto.client.order.OrderRequestDto;
//import com.example.demo.dto.client.order.OrderResponseDto;
//import com.example.demo.model.User;
//import com.example.demo.model.cartItem.CartItem;
//import com.example.demo.model.order.Order;
//import com.example.demo.repository.CartItemRepository;
//import com.example.demo.repository.ProductRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.order.OrderRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@Transactional
//public class AdminOrderServiceImpl implements AdminOrderService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//
//    @Override
//    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
//        User user = userRepository.findByEmail(clientEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<CartItem> cartItems = cartItemRepository.findByUserEmailAndOrderedFalse(clientEmail);
//
//        if (cartItems.isEmpty()) {
//            throw new RuntimeException("No items in cart to place an order");
//        }
//
//        Order order = new Order();
//        order.setUser(String.valueOf(user));
//        order.setCreatedAt(LocalDateTime.now());
//        order.setStatus("PENDING");
//        order.setShippingAddress(orderRequest.getShippingAddress());
//        order.setPaymentMethod(orderRequest.getPaymentMethod());
//        order.setAdditionalNote(orderRequest.getAdditionalNote());
//
//        order = orderRepository.save(order);
//
//        for (CartItem item : cartItems) {
//            item.setOrdered(true);
//            item.setOrderedAt(LocalDateTime.now());
//            item.setOrder(order);
//        }
//
//        cartItemRepository.saveAll(cartItems);
//    }
//
//
//    @Override
//    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
//        List<CartItem> orderedItems = cartItemRepository.findByUserEmailAndOrderedTrue(clientEmail);
//
//        return orderedItems.stream().map(item -> {
//            OrderResponseDto dto = new OrderResponseDto();
//            dto.setProductName(item.getProductName());  // safer than calling getProduct().getProduct_name()
//            dto.setQuantity(item.getQuantity());
//
//            dto.setTotalPrice(item.getQuantity() * item.getPrice());
//
//            dto.setOrderedAt(item.getOrderedAt());
//            return dto;
//        }).toList();
//    }
//
//    @Override
//    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
//        return null;
//    }
//
//    @Override
//    public void cancelOrder(Long orderId, String clientEmail) {
//    }
//}

//package com.example.demo.service.admin.order;
//
//import com.example.demo.dto.client.order.OrderDetailsDto;
//import com.example.demo.dto.client.order.OrderRequestDto;
//import com.example.demo.dto.client.order.OrderResponseDto;
//import com.example.demo.model.User;
//import com.example.demo.model.cartItem.CartItem;
//import com.example.demo.model.order.Order;
//import com.example.demo.model.order.OrderItem;
//import com.example.demo.repository.CartItemRepository;
//import com.example.demo.repository.ProductRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.order.OrderItemRepository;
//import com.example.demo.repository.order.OrderRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class AdminOrderServiceImpl implements AdminOrderService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Override
//    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
//        // 1. Find user
//        User user = userRepository.findByEmail(clientEmail)
//                .orElseThrow(() -> new RuntimeException("User not found: " + clientEmail));
//
//        // 2. Get cart items that are not yet ordered
//        List<CartItem> cartItems = cartItemRepository.findByUserEmailAndOrderedFalse(clientEmail);
//
//        if (cartItems.isEmpty()) {
//            throw new RuntimeException("No items in cart to place an order");
//        }
//
//        // 3. Create order
//        Order order = new Order();
//        order.setUser(String.valueOf(user));
//        order.setClientEmail(clientEmail);
//        order.setUserId(user.getId());
//        order.setCreatedAt(LocalDateTime.now());
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus("PENDING");
//        order.setShippingAddress(orderRequest.getShippingAddress());
//        order.setPaymentMethod(orderRequest.getPaymentMethod());
//        order.setAdditionalNote(orderRequest.getAdditionalNote());
//
//        // 4. Save order to get ID
//        order = orderRepository.save(order);
//
//        // 5. Create order items from cart items
//        double totalAmount = 0.0;
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for (CartItem cartItem : cartItems) {
//            // Create order item
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setProductId(cartItem.getProduct() != null ? cartItem.getProduct().getId() : null);
//            orderItem.setProductName(cartItem.getProductName());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setUnitPrice(cartItem.getPrice());
//            orderItem.setCreatedAt(LocalDateTime.now());
//
//            orderItems.add(orderItem);
//
//            // Update cart item
//            cartItem.setOrdered(true);
//            cartItem.setOrderedAt(LocalDateTime.now());
//            cartItem.setOrder(order);
//
//            // Calculate total
//            totalAmount += cartItem.getPrice() * cartItem.getQuantity();
//        }
//
//        // 6. Save all
//        orderItemRepository.saveAll(orderItems);
//        cartItemRepository.saveAll(cartItems);
//
//        System.out.println("Order created: ID=" + order.getId() +
//                ", User=" + clientEmail +
//                ", Items=" + cartItems.size() +
//                ", Total=" + totalAmount);
//    }
//
//    @Override
//    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
//        // Get all orders for this user
//        List<Order> orders = orderRepository.findByClientEmail(clientEmail);
//
//        if (orders.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        List<OrderResponseDto> response = new ArrayList<>();
//
//        for (Order order : orders) {
//            // Get order items for this order
//            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
//
//            OrderResponseDto dto = new OrderResponseDto();
//
//            // Set ORDER information
//            dto.setOrderId(order.getId());
//            dto.setStatus(order.getStatus());
//            dto.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : order.getCreatedAt());
//            dto.setShippingAddress(order.getShippingAddress());
//            dto.setPaymentMethod(order.getPaymentMethod());
//            dto.setClientEmail(order.getClientEmail());
//            dto.setUserId(order.getUserId());
//
//            // Calculate total from order items
//            double total = 0.0;
//            int totalQuantity = 0;
//            String firstProductName = null;
//
//            if (!orderItems.isEmpty()) {
//                for (OrderItem item : orderItems) {
//                    total += (item.getUnitPrice() != null ? item.getUnitPrice() : 0.0) * item.getQuantity();
//                    totalQuantity += item.getQuantity();
//                    if (firstProductName == null) {
//                        firstProductName = item.getProductName();
//                    }
//                }
//                dto.setProductName(firstProductName);
//                dto.setQuantity(totalQuantity);
//            }
//
//            dto.setTotalAmount(total);
//            dto.setOrderedAt(order.getCreatedAt());
//
//            response.add(dto);
//        }
//
//        return response;
//    }
//
//    @Override
//    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
//        // Verify order belongs to client
//        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
//                .orElseThrow(() -> new RuntimeException("Order not found or access denied"));
//
//        // Get order items
//        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
//
//        OrderDetailsDto details = new OrderDetailsDto();
//        details.setOrderId(order.getId());
//        details.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : order.getCreatedAt());
//        details.setStatus(order.getStatus());
//        details.setShippingAddress(order.getShippingAddress());
//        details.setPaymentMethod(order.getPaymentMethod());
//        details.setAdditionalNote(order.getAdditionalNote());
//        details.setClientEmail(order.getClientEmail());
//
//        // Create item details
//        List<OrderDetailsDto.OrderItemDetailDto> itemDetails = new ArrayList<>();
//        double total = 0.0;
//
//        for (OrderItem item : orderItems) {
//            OrderDetailsDto.OrderItemDetailDto itemDto = new OrderDetailsDto.OrderItemDetailDto();
//            itemDto.setProductId(item.getProductId());
//            itemDto.setProductName(item.getProductName());
//            itemDto.setQuantity(item.getQuantity());
//            itemDto.setUnitPrice(item.getUnitPrice());
//
//            double itemTotal = (item.getUnitPrice() != null ? item.getUnitPrice() : 0.0) * item.getQuantity();
//            itemDto.setTotalPrice(itemTotal);
//
//            itemDetails.add(itemDto);
//            total += itemTotal;
//        }
//
//        details.setItems(itemDetails);
//        details.setTotalAmount(total);
//
//        return details;
//    }
//
//    @Override
//    public void cancelOrder(Long orderId, String clientEmail) {
//        // Verify order belongs to client
//        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
//                .orElseThrow(() -> new RuntimeException("Order not found or access denied"));
//
//        // Check if order can be cancelled
//        if (!"PENDING".equals(order.getStatus())) {
//            throw new RuntimeException("Only PENDING orders can be cancelled");
//        }
//
//        // Update order status
//        order.setStatus("CANCELLED");
//        orderRepository.save(order);
//
//        // Mark cart items as not ordered
//        List<CartItem> cartItems = cartItemRepository.findByOrderId(orderId);
//        for (CartItem item : cartItems) {
//            item.setOrdered(false);
//            item.setOrder(null);
//            item.setOrderedAt(null);
//        }
//        cartItemRepository.saveAll(cartItems);
//
//        System.out.println("Order cancelled: ID=" + orderId);
//    }
//}


package com.example.demo.service.admin.order;


import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.order.OrderItemRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.admin.order.AdminOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void createOrder(OrderRequestDto orderRequest, String clientEmail) {
        User user = userRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + clientEmail));

        List<CartItem> cartItems = cartItemRepository.findByUserEmailAndOrderedFalse(clientEmail);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("No items in cart to place an order");
        }

        Order order = new Order();
        order.setUser(user);
        order.setClientEmail(clientEmail);
        // Remove: order.setUserId(user.getId()); // This method doesn't exist
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setAdditionalNote(orderRequest.getAdditionalNote());

        order = orderRepository.save(order);

        double totalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProduct() != null ? cartItem.getProduct().getId() : null);
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice()); // Use setPrice() NOT setUnitPrice()
            orderItem.setCreatedAt(LocalDateTime.now());

            orderItems.add(orderItem);

            cartItem.setOrdered(true);
            cartItem.setOrderedAt(LocalDateTime.now());
            cartItem.setOrder(order);

            totalAmount += cartItem.getPrice() * cartItem.getQuantity();
        }

        orderItemRepository.saveAll(orderItems);
        cartItemRepository.saveAll(cartItems);

        System.out.println("Order created: ID=" + order.getId() +
                ", User=" + clientEmail +
                ", Items=" + cartItems.size() +
                ", Total=" + totalAmount);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        List<OrderResponseDto> response = new ArrayList<>();

        for (Order order : orders) {
            // FIX: Use orderItemRepository instead of orderRepository
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

            OrderResponseDto dto = new OrderResponseDto();

            // Set basic order info
            dto.setOrderId(order.getId());
            dto.setStatus(order.getStatus());
            dto.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : order.getCreatedAt());
            dto.setShippingAddress(order.getShippingAddress());
            dto.setPaymentMethod(order.getPaymentMethod());
            dto.setClientEmail(order.getClientEmail());

            // REMOVED: setCustomerName() - This field doesn't exist in OrderResponseDto

            // Calculate totals from order items
            double total = 0.0;
            int totalQuantity = 0;
            String firstProductName = null;

            if (!orderItems.isEmpty()) {
                for (OrderItem item : orderItems) {
                    double itemPrice = item.getPrice() != null ? item.getPrice() : 0.0;
                    total += itemPrice * item.getQuantity();
                    totalQuantity += item.getQuantity();

                    if (firstProductName == null) {
                        firstProductName = item.getProductName();
                    }
                }
                dto.setProductName(firstProductName);
                dto.setQuantity(totalQuantity);
            }

            // Set the calculated totals - choose one of these based on your DTO:
            // If you want to use totalAmount field:
            dto.setTotalAmount(total);

            // Or if you want to use totalPrice field:
            dto.setTotalPrice(total);

            // Set the ordered date
            dto.setOrderedAt(order.getCreatedAt());

            response.add(dto);
        }

        return response;
    }


    @Override
    public List<OrderResponseDto> getOrdersByClientEmail(String clientEmail) {
        List<Order> orders = orderRepository.findByClientEmail(clientEmail);

        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        List<OrderResponseDto> response = new ArrayList<>();

        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

            OrderResponseDto dto = new OrderResponseDto();
            dto.setOrderId(order.getId());
            dto.setStatus(order.getStatus());
            dto.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : order.getCreatedAt());
            dto.setShippingAddress(order.getShippingAddress());
            dto.setPaymentMethod(order.getPaymentMethod());
            dto.setClientEmail(order.getClientEmail());
            // Remove: dto.setUserId(order.getUserId()); // This method doesn't exist in OrderResponseDto

            double total = 0.0;
            int totalQuantity = 0;
            String firstProductName = null;

            if (!orderItems.isEmpty()) {
                for (OrderItem item : orderItems) {
                    total += (item.getPrice() != null ? item.getPrice() : 0.0) * item.getQuantity(); // Use getPrice() NOT getUnitPrice()
                    totalQuantity += item.getQuantity();
                    if (firstProductName == null) {
                        firstProductName = item.getProductName();
                    }
                }
                dto.setProductName(firstProductName);
                dto.setQuantity(totalQuantity);
            }

            dto.setTotalAmount(total);
            dto.setOrderedAt(order.getCreatedAt());
            response.add(dto);
        }

        return response;
    }

    @Override
    public OrderDetailsDto getOrderDetails(Long orderId, String clientEmail) {
        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
                .orElseThrow(() -> new RuntimeException("Order not found or access denied"));

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        OrderDetailsDto details = new OrderDetailsDto();
        details.setOrderId(order.getId());
        details.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : order.getCreatedAt());
        details.setStatus(order.getStatus());
        details.setShippingAddress(order.getShippingAddress());
        details.setPaymentMethod(order.getPaymentMethod());
        details.setAdditionalNote(order.getAdditionalNote());
        details.setClientEmail(order.getClientEmail());

        // Use List<OrderDetailsDto.ItemDetail> instead of OrderItemDetailDto
        List<OrderDetailsDto.ItemDetail> itemDetails = new ArrayList<>();
        double total = 0.0;

        for (OrderItem item : orderItems) {
            OrderDetailsDto.ItemDetail itemDto = new OrderDetailsDto.ItemDetail(); // Use ItemDetail
            itemDto.setProductId(item.getProductId());
            itemDto.setProductName(item.getProductName());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice()); // Use setPrice() NOT setUnitPrice()

            double itemTotal = (item.getPrice() != null ? item.getPrice() : 0.0) * item.getQuantity();
            itemDto.setTotalPrice(itemTotal);

            itemDetails.add(itemDto);
            total += itemTotal;
        }

        details.setItems(itemDetails);
        details.setTotalAmount(total);

        return details;
    }

    @Override
    public void cancelOrder(Long orderId, String clientEmail) {
        Order order = orderRepository.findByIdAndClientEmail(orderId, clientEmail)
                .orElseThrow(() -> new RuntimeException("Order not found or access denied"));

        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("Only PENDING orders can be cancelled");
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        List<CartItem> cartItems = cartItemRepository.findByOrderId(orderId);
        for (CartItem item : cartItems) {
            item.setOrdered(false);
            item.setOrder(null);
            item.setOrderedAt(null);
        }
        cartItemRepository.saveAll(cartItems);

        System.out.println("Order cancelled: ID=" + orderId);
    }

//    update status
    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Optional validation
        if (!status.equalsIgnoreCase("PENDING") &&
                !status.equalsIgnoreCase("COMPLETED") &&
                !status.equalsIgnoreCase("CANCELLED")) {
            throw new RuntimeException("Invalid status value");
        }

        order.setStatus(status.toUpperCase());
        orderRepository.save(order);

        System.out.println("Order status updated: ID=" + orderId + " -> " + status);
    }

}


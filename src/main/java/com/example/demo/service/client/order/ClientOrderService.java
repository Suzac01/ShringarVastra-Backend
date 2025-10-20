package com.example.demo.service.client.order;

import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.dto.client.order.OrderDetailsDto;

import java.util.List;

public interface ClientOrderService {
    void createOrder(OrderRequestDto orderRequest, String clientEmail);
    List<OrderResponseDto> getOrdersByClientEmail(String clientEmail);
    OrderDetailsDto getOrderDetails(Long orderId, String clientEmail);
    void cancelOrder(Long orderId, String clientEmail);
}

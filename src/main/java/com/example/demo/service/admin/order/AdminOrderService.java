package com.example.demo.service.admin.order;

import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminOrderService {

    void createOrder(OrderRequestDto orderRequest, String clientEmail);


        List<OrderResponseDto> getOrdersByClientEmail(String clientEmail);
        OrderDetailsDto getOrderDetails(Long orderId, String clientEmail);
        void cancelOrder(Long orderId, String clientEmail);

        List<OrderResponseDto> getAllOrders();

}

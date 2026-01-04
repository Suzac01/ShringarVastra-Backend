package com.example.demo.controller.admin.order;

import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.service.admin.order.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @RequestBody OrderRequestDto orderRequest,
            @RequestParam String clientEmail) {
        adminOrderService.createOrder(orderRequest, clientEmail);
        return ResponseEntity.ok("Order created successfully.");
    }

    @GetMapping("/clientOrders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByClientEmail(
            @RequestParam String clientEmail) {
        List<OrderResponseDto> orders = adminOrderService.getOrdersByClientEmail(clientEmail);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(
            @PathVariable Long orderId,
            @RequestParam String clientEmail) {
        OrderDetailsDto details = adminOrderService.getOrderDetails(orderId, clientEmail);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId,
            @RequestParam String clientEmail) {
        adminOrderService.cancelOrder(orderId, clientEmail);
        return ResponseEntity.ok("Order cancelled successfully.");
    }
}

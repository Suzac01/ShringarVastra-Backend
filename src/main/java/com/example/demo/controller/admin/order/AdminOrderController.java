package com.example.demo.controller.admin.order;

import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.dto.client.order.UpdateOrderStatusDto;
import com.example.demo.dto.client.order.orderConfirmationDTO.OrderConfirmationDTO;
import com.example.demo.service.admin.order.AdminOrderService;
import com.example.demo.service.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequest) {
        // Get clientEmail from the DTO instead of @RequestParam
        String clientEmail = orderRequest.getClientEmail();
        adminOrderService.createOrder(orderRequest, clientEmail);
        return ResponseEntity.ok("Order created successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        List<OrderResponseDto> orders = adminOrderService.getAllOrders();
        return ResponseEntity.ok(orders);

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

    @PostMapping("/send-confirmation")
    public ResponseEntity<?> sendOrderConfirmation(@RequestBody OrderConfirmationDTO orderConfirmation) {
        try {
            // Validate required fields
            if (orderConfirmation.getCustomerEmail() == null || orderConfirmation.getCustomerEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Customer email is required"));
            }

            if (orderConfirmation.getOrderId() == null || orderConfirmation.getOrderId().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Order ID is required"));
            }

            // Set order date if not provided
            if (orderConfirmation.getOrderDate() == null) {
                orderConfirmation.setOrderDate(LocalDateTime.now());
            }

            // Send confirmation email
            emailService.sendOrderConfirmationEmail(orderConfirmation.getCustomerEmail(), orderConfirmation);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Order confirmation email sent successfully");
            response.put("orderId", orderConfirmation.getOrderId());
            response.put("sentTo", orderConfirmation.getCustomerEmail());
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to send order confirmation: " + e.getMessage()));
        }
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", message);
        return errorResponse;
    }

    @PutMapping("/status/{orderId}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusDto dto) {

        adminOrderService.updateOrderStatus(orderId, dto.getStatus());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Order status updated");
        response.put("orderId", orderId);
        response.put("newStatus", dto.getStatus());

        return ResponseEntity.ok(response);
    }

}




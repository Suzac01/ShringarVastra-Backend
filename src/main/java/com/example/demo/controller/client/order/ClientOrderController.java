package com.example.demo.controller.client.order;

import com.example.demo.dto.client.order.OrderDetailsDto;
import com.example.demo.dto.client.order.OrderRequestDto;
import com.example.demo.dto.client.order.OrderResponseDto;
import com.example.demo.service.client.order.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/client")
public class ClientOrderController {

    @Autowired
    private ClientOrderService clientOrderService;

    private static final boolean BYPASS_AUTH = true;

    @PostMapping("/confirmOrder")
    public ResponseEntity<String> confirmOrder(@RequestBody OrderRequestDto orderRequest, Principal principal) {
        String userEmail;
        if (BYPASS_AUTH) {
            userEmail = "john@example.com";  // Hardcoded user for testing
        } else {
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
            }
            userEmail = principal.getName();
        }

        clientOrderService.createOrder(orderRequest, userEmail);
        return ResponseEntity.ok("Order placed successfully for user: " + userEmail);
    }

    // Get all orders of the client
    @GetMapping("/getOrders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(Principal principal) {
        List<OrderResponseDto> orders = clientOrderService.getOrdersByClientEmail(principal.getName());
        return ResponseEntity.ok(orders);
    }

    // Get details of a specific order
    @GetMapping("/getOrderDetails/{orderId}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long orderId, Principal principal) {
        OrderDetailsDto orderDetails = clientOrderService.getOrderDetails(orderId, principal.getName());
        return ResponseEntity.ok(orderDetails);
    }

    // Cancel a specific order
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId, Principal principal) {
        clientOrderService.cancelOrder(orderId, principal.getName());
        return ResponseEntity.ok("Order canceled successfully.");
    }
}

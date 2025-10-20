package com.example.demo.controller.client;

import com.example.demo.dto.cart.AddToCartRequest;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.service.client.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client/cart")
public class CartController {

    @Autowired
    private CartService cartService;
//    Cart CRUD Operations.
    @PostMapping("/add")
    public String addToCart(@RequestBody AddToCartRequest request) {

        System.out.println("userId = " + request.getUserId());
        System.out.println("productId = " + request.getProductId());
        System.out.println("quantity = " + request.getQuantity());

        cartService.addToCart(request.getUserEmail(), request.getProductId(), request.getQuantity());

        return "Item added to cart.";
    }

    @GetMapping("get/{userId}")
    public List<CartItem> getUserCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "Item removed.";
    }

    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared.";
    }
}

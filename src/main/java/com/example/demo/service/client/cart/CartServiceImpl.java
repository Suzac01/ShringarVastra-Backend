package com.example.demo.service.client.cart;

import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.model.cartItem.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addToCart(String userEmail, Long productId, int quantity) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setProductName(product.getProductName());
        cartItem.setImageUrl(product.getProductImage());
        try {
            cartItem.setPrice((product.getProductPrice()));
        } catch (Exception e) {
            cartItem.setPrice(0.0);
        }
        cartItem.setOrdered(false);

        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getUserCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
                System.out.println("Fetching cart for user: " + user.getId() + " - " + user.getEmail());

        // Fetch only cart items where ordered is false
        return cartItemRepository.findByUserAndOrderedFalse(user);

    }

    @Override
    public void removeItem(Long cartItemId) {
        boolean exists = cartItemRepository.existsById(cartItemId);
        if (!exists) {
            throw new RuntimeException("Cart item not found with ID: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        cartItemRepository.deleteByUser(user);
    }
}

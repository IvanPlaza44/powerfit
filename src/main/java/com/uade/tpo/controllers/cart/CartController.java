package com.uade.tpo.controllers.cart;

import com.uade.tpo.entity.CartDetail;
import com.uade.tpo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDetail>> getCart(@PathVariable Long userId) {
        List<CartDetail> details = cartService.getCartByUserId(userId);
        return details.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(details);
    }

    @PostMapping("/{userId}/products")
    public ResponseEntity<List<CartDetail>> addProduct(@PathVariable Long userId, @RequestBody CartProductRequest request) {
        return ResponseEntity.ok(cartService.addProduct(userId, request));
    }

    @PutMapping("/{userId}/products/{productId}")
    public ResponseEntity<List<CartDetail>> updateQuantity(
            @PathVariable Long userId, 
            @PathVariable Long productId, 
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateProductQuantity(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProduct(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
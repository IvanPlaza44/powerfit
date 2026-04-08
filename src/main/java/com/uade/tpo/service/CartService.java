package com.uade.tpo.service;

import com.uade.tpo.entity.*;
import com.uade.tpo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository; //  Busca al dueño del carrito

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    // Buscamos al usuario real en la DB
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    
                    Cart newCart = new Cart();
                    newCart.setUser(user); // SETEAMOS EL USUARIO
                    newCart.setDetails(new ArrayList<>()); 
                    return cartRepository.save(newCart);
                });
    }

    public String addProductToCart(Long userId, Long productId, int quantity) {
        // Validamos Producto y Stock
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (product.getStock() < quantity) {
            return "Error: No hay suficiente stock disponible.";
        }

        // Obtenemos el carrito del usuario
        Cart cart = getCartByUserId(userId);

        // LOGICA PARA GUARDAR EL DETALLE 
        CartDetail detail = new CartDetail();
        detail.setCart(cart);
        detail.setProduct(product);
        detail.setQuantity(quantity);
        
        // Detalle a la lista del carrito
        cart.getDetails().add(detail);

        // GUARDAR CAMBIOS
        cartRepository.save(cart); 
        
        return "Producto '" + product.getName() + "' agregado al carrito exitosamente.";
    }
}
package com.uade.tpo.service;

import com.uade.tpo.controllers.cart.CartProductRequest;
import com.uade.tpo.entity.CartDetail;

import java.util.List;

public interface CartService {

    //Devolver el carrito por id del usuario
    List<CartDetail> getCartByUserId(Long userId);

    //Agregar un producto al carrito de un usuario
    List<CartDetail> addProduct(Long userId, CartProductRequest request);

    //Eliminar un producto del carrito de un usuario
    void removeProduct(Long userId, Long productId);

    //Editar la cantidad de un producto de un carrito de un usuario
    List<CartDetail> updateProductQuantity(Long userId, Long productId, Integer quantity);

    //Falta vaciar un carrito de un usuario.
    void clearCart(Long userId);
}
package com.uade.tpo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Cart;
import com.uade.tpo.entity.CartDetail;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.CartProductRequest;
//import com.uade.tpo.exceptions.ResourceNotFoundException;
import com.uade.tpo.repository.CartDetailRepository;
import com.uade.tpo.repository.CartRepository;

@Service
public class CartServiceImp implements CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> getCartByUserId(Long userId) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return cartDetailRepository.findByCart_Id(cart.getId());
    }

    @Override
    public List<CartDetail> addProduct(Long userId, CartProductRequest request) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user = new User();
                    user.setId(userId);
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartDetail detail = cartDetailRepository
                .findByCart_IdAndProduct_Id(cart.getId(), request.getProductId())
                .orElseGet(() -> {
                    CartDetail newDetail = new CartDetail();
                    newDetail.setCart(cart);

                    Product product = new Product();
                    product.setId(request.getProductId());

                    newDetail.setProduct(product);
                    newDetail.setQuantity(0);
                    return newDetail;
                });

        detail.setQuantity(detail.getQuantity() + request.getQuantity());

        cartDetailRepository.save(detail);

        return cartDetailRepository.findByCart_Id(cart.getId());
    }

    @Override
    public void removeProduct(Long userId, Long productId) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartDetail detail = cartDetailRepository
                .findByCart_IdAndProduct_Id(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en carrito"));

        cartDetailRepository.delete(detail);
    }

    @Override
    public List<CartDetail> updateProductQuantity(Long userId, Long productId, Integer quantity) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartDetail detail = cartDetailRepository
                .findByCart_IdAndProduct_Id(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        detail.setQuantity(quantity);

        cartDetailRepository.save(detail);

        return cartDetailRepository.findByCart_Id(cart.getId());
    }
  }

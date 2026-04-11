package com.uade.tpo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Cart;
import com.uade.tpo.entity.CartDetail;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.CartProductRequest;
import com.uade.tpo.repository.CartDetailRepository;
import com.uade.tpo.repository.CartRepository;
import com.uade.tpo.repository.ProductRepository;
import com.uade.tpo.repository.UserRepository;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    //Devolver el carrito por id del usuario
    @Override
    public List<CartDetail> getCartByUserId(Long userId) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return cartDetailRepository.findByCart_Id(cart.getId());
    }

    //Agregar un producto al carrito de un usuario
    @Override
    public List<CartDetail> addProduct(Long userId, CartProductRequest request) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartDetail detail = cartDetailRepository
                .findByCart_IdAndProduct_Id(cart.getId(), request.getProductId())
                .orElseGet(() -> {
                    CartDetail newDetail = new CartDetail();
                    newDetail.setCart(cart);

                    Product product = productRepository.findById(request.getProductId())
    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                    newDetail.setProduct(product);
                    newDetail.setQuantity(0);
                    return newDetail;
                });

        detail.setQuantity(detail.getQuantity() + request.getQuantity());

        cartDetailRepository.save(detail);

        return cartDetailRepository.findByCart_Id(cart.getId());
    }

    //Eliminar un producto del carrito de un usuario
    @Override
    public void removeProduct(Long userId, Long productId) {

        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartDetail detail = cartDetailRepository
                .findByCart_IdAndProduct_Id(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en carrito"));

        cartDetailRepository.delete(detail);
    }

    //Editar la cantidad de un producto de un carrito de un usuario
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
//Vacia el carrito
@Override
public void clearCart(Long userId) {
    Cart cart = cartRepository.findByUser_Id(userId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    cartDetailRepository.deleteByCart_Id(cart.getId());
}
  }

package com.uade.tpo.service;

import com.uade.tpo.controllers.purchase.PurchaseRequest;
import com.uade.tpo.entity.*;
import com.uade.tpo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PurchaseServiceImp implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public Purchase performPurchase(PurchaseRequest request) {

        System.out.println("USER ID: " + request.getUserId());
        System.out.println("USERS: " + userRepository.findAll());

        User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cart cart = cartRepository.findByUser_Id(user.getId())
        .orElseThrow(() -> new RuntimeException("El carrito no existe"));

        if (cart.getDetails().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        
        }

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setDate(LocalDateTime.now());
        purchase.setDetails(new ArrayList<>());

        double total = 0;

        for (CartDetail cartDetail : cart.getDetails()) {

            Product product = cartDetail.getProduct();
            double finalPrice = product.getPrice();

            if (product.getDiscount() > 0) {
                finalPrice = finalPrice - (finalPrice * product.getDiscount() / 100);
            }

            // validar stock
            if (product.getStock() < cartDetail.getQuantity()) {
                throw new RuntimeException("Sin stock para: " + product.getName());
            }

            // descontar stock
            product.setStock(product.getStock() - cartDetail.getQuantity());
            productRepository.save(product);

            // crear detalle
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setPurchase(purchase);
            purchaseDetail.setProduct(product);
            purchaseDetail.setQuantity(cartDetail.getQuantity());
            purchaseDetail.setPrice(finalPrice);
            purchase.getDetails().add(purchaseDetail);

            total += finalPrice * cartDetail.getQuantity();
        }

        purchase.setTotal(total);

        // limpiar carrito
        cart.getDetails().clear();

        return purchaseRepository.save(purchase);
    }


}
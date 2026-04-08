package com.uade.tpo.service;

import com.uade.tpo.entity.Purchase;
import com.uade.tpo.entity.PurchaseDetail;
import com.uade.tpo.entity.User;
import com.uade.tpo.entity.Cart;
import com.uade.tpo.entity.CartDetail;
import com.uade.tpo.entity.Product;
import com.uade.tpo.repository.PurchaseRepository;
import com.uade.tpo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Purchase performPurchase(User user, Cart cart) {
        if (cart.getDetails().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setDate(LocalDateTime.now());
        purchase.setDetails(new ArrayList<>());
        double total = 0;

        for (CartDetail cartDetail : cart.getDetails()) {
            // Validar stock
            if (cartDetail.getProduct().getStock() < cartDetail.getQuantity()) {
                throw new RuntimeException("Sin stock para: " + cartDetail.getProduct().getName());
            }

            // Descontar stock
            cartDetail.getProduct().setStock(cartDetail.getProduct().getStock() - cartDetail.getQuantity());
            productRepository.save(cartDetail.getProduct());

            // Crear el detalle de la compra
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setPurchase(purchase);
            purchaseDetail.setProduct(cartDetail.getProduct());
            purchaseDetail.setQuantity(cartDetail.getQuantity());
            purchaseDetail.setPrice(cartDetail.getProduct().getPrice());
            
            purchase.getDetails().add(purchaseDetail);
            total += (purchaseDetail.getPrice() * purchaseDetail.getQuantity());
        }

        purchase.setTotal(total);
        return purchaseRepository.save(purchase);
    }
}
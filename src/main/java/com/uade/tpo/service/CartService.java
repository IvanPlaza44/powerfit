package com.uade.tpo.service;

import com.uade.tpo.entity.*;
import com.uade.tpo.entity.CartDetail;
import com.uade.tpo.entity.dto.CartProductRequest;
import com.uade.tpo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

public interface CartService {

    List<CartDetail> getCartByUserId(Long userId);

    List<CartDetail> addProduct(Long userId, CartProductRequest request);

    void removeProduct(Long userId, Long productId);

    List<CartDetail> updateProductQuantity(Long userId, Long productId, Integer quantity);
}
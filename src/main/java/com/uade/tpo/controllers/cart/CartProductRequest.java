package com.uade.tpo.controllers.cart;

import lombok.Data;

@Data
public class CartProductRequest {
    private Long productId;
    private Integer quantity;
    
}

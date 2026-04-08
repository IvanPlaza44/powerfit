package com.uade.tpo.entity.dto;

import lombok.Data;

@Data
public class CartProductRequest {
    private Long productId;
    private Integer quantity;
    
}

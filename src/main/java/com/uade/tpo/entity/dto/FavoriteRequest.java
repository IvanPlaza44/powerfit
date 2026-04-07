package com.uade.tpo.entity.dto;

import lombok.Data;

@Data
public class FavoriteRequest {
    private Long userId;
    private Long productId;
}
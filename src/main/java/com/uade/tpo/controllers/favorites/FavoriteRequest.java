package com.uade.tpo.controllers.favorites;

import lombok.Data;

@Data
public class FavoriteRequest {
    private Long userId;
    private Long productId;
}
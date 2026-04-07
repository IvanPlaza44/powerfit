package com.uade.tpo.service;

import java.util.List;
import com.uade.tpo.entity.Favorite;
import com.uade.tpo.entity.dto.FavoriteRequest;

public interface FavoriteService {
    List<Favorite> getFavoritesByUserId(Long userId);
    Favorite addFavorite(FavoriteRequest favoriteRequest);
    void removeFavorite(Long favoriteId);
}
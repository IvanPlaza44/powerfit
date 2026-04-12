package com.uade.tpo.service;

import java.util.List;

import com.uade.tpo.controllers.favorites.FavoriteRequest;
import com.uade.tpo.entity.Favorite;

public interface FavoriteService {

    //Traer todos los Favoritos por el user ID
    List<Favorite> getFavoritesByUserId(Long userId);

    //Agregar un Favorito
    Favorite addFavorite(FavoriteRequest favoriteRequest);

    //Eliminar un favorito
    void removeFavorite(Long favoriteId);
}
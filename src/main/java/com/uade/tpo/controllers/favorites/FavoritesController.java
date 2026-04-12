package com.uade.tpo.controllers.favorites;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.entity.Favorite;
import com.uade.tpo.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoriteService favoriteService;

    // Obtener favoritos de un usuario: GET /favorites/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getFavorites(@PathVariable Long userId) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        if (favorites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favorites);
    }

    // Agregar a favoritos: POST /favorites
    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody FavoriteRequest favoriteRequest) {
        return ResponseEntity.ok(favoriteService.addFavorite(favoriteRequest));
    }

    // Quitar de favoritos: DELETE /favorites/5
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long favoriteId) {
        favoriteService.removeFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }
}
package com.uade.tpo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Favorite;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.FavoriteRequest;
import com.uade.tpo.repository.FavoriteRepository;
import com.uade.tpo.repository.ProductRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Favorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public Favorite addFavorite(FavoriteRequest request) {
        // 1. Buscamos el producto por el ID que viene en el DTO
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 2. Validamos duplicados usando los IDs
        if (favoriteRepository.existsByUserIdAndProductId(request.getUserId(), request.getProductId())) {
            throw new RuntimeException("El producto ya está en tus favoritos");
        }

        // 3. Creamos la entidad vinculando el userId y el objeto Product completo
        Favorite favorite = new Favorite(request.getUserId(), product);
        
        return favoriteRepository.save(favorite);
    }

    @Override
    public void removeFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
}
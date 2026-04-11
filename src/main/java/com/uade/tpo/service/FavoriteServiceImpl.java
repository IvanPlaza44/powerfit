package com.uade.tpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Favorite;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.User;
import com.uade.tpo.entity.dto.FavoriteRequest;
import com.uade.tpo.repository.FavoriteRepository;
import com.uade.tpo.repository.ProductRepository;
import com.uade.tpo.repository.UserRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Favorite addFavorite(FavoriteRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (favoriteRepository.existsByUserAndProduct(user, product)) {
            throw new RuntimeException("El producto ya está en tu lista de favoritos");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);

        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> getFavoritesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return favoriteRepository.findByUser(user);
    }

    @Override
    public void removeFavorite(Long favoriteId) {
        if (!favoriteRepository.existsById(favoriteId)) {
            throw new RuntimeException("El favorito no existe");
        }
        favoriteRepository.deleteById(favoriteId);
    }
}
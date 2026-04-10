package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Favorite;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    //Para ver la lista de Favoritos del usuario
    List<Favorite> findByUser(User user);
    
    // Para verificar si ya lo agrego y evitar los duplicados
    boolean existsByUserAndProduct(User user, Product product);

    // Para borrar un producto específico de los favoritos de un usuario
    void deleteByUserAndProduct(User user, Product product);
}
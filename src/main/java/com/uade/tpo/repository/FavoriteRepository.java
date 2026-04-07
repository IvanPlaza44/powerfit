package com.uade.tpo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    
    // Spring JPA entiende que "ProductId" entra a la entidad Product y busca su ID
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
package com.uade.tpo.repository;

import com.uade.tpo.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // Permite buscar todas las compras de un usuario (para el historial)
    List<Purchase> findByUserId(Long userId);
}
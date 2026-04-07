package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Para cumplir con el requisito de no ver productos sin stock
    @Query("select p from Product p where p.stock > 0")
    Page<Product> findAvailableProducts(PageRequest pageable);

    @Query("select p from Product p where p.name like %?1% and p.stock > 0")
    List<Product> findByName(String name);
}
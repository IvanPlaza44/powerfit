package com.uade.tpo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.CartDetail;

import jakarta.transaction.Transactional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    List<CartDetail> findByCart_Id(Long cartId);

    Optional<CartDetail> findByCart_IdAndProduct_Id(Long cartId, Long productId);

    @Transactional
    @Modifying
    void deleteByCart_Id(Long cartId);
}

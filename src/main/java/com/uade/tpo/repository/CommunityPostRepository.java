package com.uade.tpo.repository;
import com.uade.tpo.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository
        extends JpaRepository<CommunityPost, Long> {
}
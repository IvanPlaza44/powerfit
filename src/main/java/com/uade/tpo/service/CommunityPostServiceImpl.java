package com.uade.tpo.service;
import com.uade.tpo.controllers.Community.CommunityPostRequest;
import com.uade.tpo.entity.CommunityPost;
import com.uade.tpo.repository.CommunityPostRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityPostServiceImpl
        implements CommunityPostService {

    private final CommunityPostRepository repository;

    public CommunityPostServiceImpl(
            CommunityPostRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public CommunityPost createPost(
            CommunityPostRequest request
    ) {

        CommunityPost post = new CommunityPost();

        post.setUsername(request.getUsername());
        post.setProduct(request.getProduct());
        post.setImage(request.getImage());
        post.setTestimonial(request.getTestimonial());

        return repository.save(post);
    }

    @Override
    public List<CommunityPost> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }
}
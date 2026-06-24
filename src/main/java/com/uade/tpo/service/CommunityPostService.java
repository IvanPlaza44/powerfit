package com.uade.tpo.service;

import com.uade.tpo.controllers.Community.CommunityPostRequest;
import com.uade.tpo.entity.CommunityPost;

import java.util.List;

public interface CommunityPostService {

    CommunityPost createPost(
            CommunityPostRequest request
    );

    List<CommunityPost> getAllPosts();

    void deletePost(Long id);
}
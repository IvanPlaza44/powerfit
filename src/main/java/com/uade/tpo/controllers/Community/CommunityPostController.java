package com.uade.tpo.controllers.Community;

import com.uade.tpo.entity.CommunityPost;
import com.uade.tpo.controllers.Community.CommunityPostRequest;
import com.uade.tpo.service.CommunityPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@CrossOrigin("*")
public class CommunityPostController {

    private final CommunityPostService service;

    public CommunityPostController(
            CommunityPostService service
    ) {
        this.service = service;
    }

    @PostMapping
    public CommunityPost createPost(
            @RequestBody CommunityPostRequest request
    ) {
        return service.createPost(request);
    }

    @GetMapping
    public List<CommunityPost> getPosts() {
        return service.getAllPosts();
    }

    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable Long id
    ) {
        service.deletePost(id);
    }
}
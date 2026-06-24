package com.uade.tpo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "community_posts")
public class CommunityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String product;

    @Column(length = 1000)
    private String image;

    @Column(length = 3000)
    private String testimonial;

    public CommunityPost() {
    }

    public CommunityPost(Long id,
                         String username,
                         String product,
                         String image,
                         String testimonial) {
        this.id = id;
        this.username = username;
        this.product = product;
        this.image = image;
        this.testimonial = testimonial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }
}
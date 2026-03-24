package com.uade.tpo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private int stock;
    private double price;

}

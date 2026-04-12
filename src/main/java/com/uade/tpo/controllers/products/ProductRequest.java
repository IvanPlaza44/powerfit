package com.uade.tpo.controllers.products;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String image;
    private double price;
    private int discount;
    private int stock;
    private Long categoryId;
    private Long sellerId; // "Usuario Vendedor"
}
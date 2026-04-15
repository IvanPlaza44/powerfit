package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String image;

    @Column(nullable = false)
    private double price;

    @Column
    private int discount;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id") // ✅ correcto
    private User seller;  
}

/*{
  "id": 1,
  "name": "Producto A",
  "description": "algo",
  "price": 1500.0,
  "stock": 10,
  "categoryId": 1,
  "sellerId": 1
}
  */
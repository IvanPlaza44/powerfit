package com.uade.tpo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.ProductRequest;

// ProductService.java
public interface ProductService {
    public Page<Product> getProducts(PageRequest pageRequest);

    public Optional<Product> getProductById(Long productId);

    public Product createProduct(ProductRequest productRequest); //agregar manejo de error por si hay duplicados!!!!!

    public Product updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);
}


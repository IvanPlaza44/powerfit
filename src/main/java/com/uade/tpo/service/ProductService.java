package com.uade.tpo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.ProductRequest;
import com.uade.tpo.exceptions.CategoryNotFoundException;
import com.uade.tpo.exceptions.ProductDuplicateException;
import com.uade.tpo.exceptions.ProductNotFoundException;

// ProductService.java
public interface ProductService {
    
    public Page<Product> getProducts(PageRequest pageRequest); //Devuelve todos los produtos 

    public Optional<Product> getProductById(Long productId); //Devuelve un producto por ID

    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException, CategoryNotFoundException; //Crea un nuevo producto

    public Product updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException;//Edita un producto ya creado

    public String deleteProduct(Long id) throws ProductNotFoundException; //Elimina un producto
}

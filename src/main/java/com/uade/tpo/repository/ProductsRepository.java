package com.uade.tpo.repository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductsRepository {

        //Metodo para traer todos los productos
    @GetMapping
    public String getAllProducts(@RequestParam String param) {
        return new String();
    };

    //Metodo para traer un producto por id
    @GetMapping("/{productId}") //localhost:8080/products/1
    public String getProductById(@PathVariable String productId) {
        return new String();
    };

    //Metodo para crear un producto
    @PostMapping("path")
    public String createProduct(@RequestBody String product) {
        return new String();
    };

    //Metodo para actualizar un producto
    @PutMapping("path/{id}")
    public String putProduct(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    };

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        return new String();
    }

    
}
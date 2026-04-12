package com.uade.tpo.controllers.products;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.entity.Product;
import com.uade.tpo.exceptions.CategoryNotFoundException;
import com.uade.tpo.exceptions.ProductDuplicateException;
import com.uade.tpo.exceptions.ProductNotFoundException;
import com.uade.tpo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    // Traer todos los productos (con paginación)
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productService.getProducts(PageRequest.of(0, 20)));
        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size)));
    }

    // Traer un producto por ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> result = productService.getProductById(productId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        
        return ResponseEntity.noContent().build();
    }

    // Crear un producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException, CategoryNotFoundException {
        Product result = productService.createProduct(productRequest);
        return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFoundException{
        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(message);
        
    }
}
package com.uade.tpo.controllers.products;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    // Traer todos los productos (paginados)
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        if (page == null || size == null) {
            return ResponseEntity.ok(productService.getProducts(PageRequest.of(0, 20)));
        }

        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size)));
    }

    // Traer producto por ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> result = productService.getProductById(productId);

        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    // Crear producto 
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody ProductRequest request,
            Authentication authentication
    ) throws ProductDuplicateException, CategoryNotFoundException, ProductNotFoundException {
        String username = authentication.getName();

        Product product = productService.createProduct(request, username);

        return ResponseEntity
                .created(URI.create("/products/" + product.getId()))
                .body(product);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request
    ) throws ProductNotFoundException {

        Product updated = productService.updateProduct(id, request);
        return ResponseEntity.ok(updated);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
            throws ProductNotFoundException {

        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(message);
    }
}
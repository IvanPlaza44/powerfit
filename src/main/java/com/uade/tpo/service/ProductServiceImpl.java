package com.uade.tpo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Category;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.ProductRequest;
import com.uade.tpo.repository.CategoryRepository;
import com.uade.tpo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getProducts(PageRequest pageable) {
        return productRepository.findAvailableProducts(pageable);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(ProductRequest request) {
        // Validación de categoría
        Category category = categoryRepository.findById(request.getCategoryId()) 
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        if (!productRepository.findByName(request.getName()).isEmpty()) {
            throw new RuntimeException("Ya existe el producto con ese nombre");
}
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setStock(request.getStock());
        product.setCategory(category);
        product.setSellerId(request.getSellerId()); // Asignamos quién lo vende
        
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id).map(product -> {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStock(request.getStock());
            product.setDiscount(request.getDiscount());
            product.setImage(request.getImage());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
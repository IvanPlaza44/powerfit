package com.uade.tpo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.controllers.products.ProductRequest;
import com.uade.tpo.entity.Category;
import com.uade.tpo.entity.Product;
import com.uade.tpo.exceptions.CategoryNotFoundException;
import com.uade.tpo.exceptions.ProductDuplicateException;
import com.uade.tpo.exceptions.ProductNotFoundException;
import com.uade.tpo.repository.CategoryRepository;
import com.uade.tpo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    //DEVOLVER TODOS LOS PRODUCTOS
    @Override
    public Page<Product> getProducts(PageRequest pageable) {
        return productRepository.findAvailableProducts(pageable);
    }

    //DEVUELVE PRODUCTO POR ID
    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    //CREA UN NUEVO PRODUCTO
    @Override
    public Product createProduct(ProductRequest request) throws ProductDuplicateException, CategoryNotFoundException{
        // Validación de categoría
        Category category = categoryRepository.findById(request.getCategoryId()) 
                .orElseThrow(() -> new CategoryNotFoundException());

        if (!productRepository.findByName(request.getName()).isEmpty()) {
            throw new ProductDuplicateException();
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

    //EDITA UN PRODUCTO PASANDOLE EL ID Y NUEVOS DATOS
    @Override
    public Product updateProduct(Long productId, ProductRequest request) throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStock(request.getStock());
            product.setDiscount(request.getDiscount());
            product.setImage(request.getImage());
            return productRepository.save(product);
        }).orElseThrow(ProductNotFoundException::new);
    }

    //ELIMINA UN PRODUCTO POR EL ID
    @Override
    public String deleteProduct(Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
        return "Producto eliminado exitosamente";
    }
}
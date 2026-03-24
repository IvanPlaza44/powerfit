package com.uade.tpo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uade.tpo.entity.Category;
import com.uade.tpo.service.CategoryService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/categories") //localhost:8080/categories
public class CategoryControllers {
    
    //Metodo para traer todas las categorias
    @GetMapping
    public ArrayList<Category> getAllCategories() {
        CategoryService categoryService = new CategoryService();
        return categoryService.getAllCategories();
    }
    //Mertodo para traer una categoria por id
    @GetMapping("/{categoryId}") //localhost:8080/categories/1
    public String getCategoryById(@PathVariable int categoryId) {
        CategoryService categoryService = new CategoryService();
        return categoryService.getCategoryById(categoryId);
    }
    //Metodo para crear una categoria
    @PostMapping("path")
    public String createCategory(@RequestBody String entity) {
        CategoryService categoryService = new CategoryService();
        return categoryService.createCategory(entity);
    }
    
    
    
}

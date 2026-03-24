package com.uade.tpo.service;

import java.util.ArrayList;
import com.uade.tpo.entity.Category;
import com.uade.tpo.repository.CategoryRepository;

public class CategoryService {

    public ArrayList<Category> getAllCategories()
    {
        CategoryRepository categoryRepository = new CategoryRepository();
        return categoryRepository.getAllCategories();
    }
    
    public String getCategoryById(int categoryId) {
        
        return null;
    }
   
    public String createCategory(String entity) {
        //TODO: process POST request
        
        return null;
    }
}

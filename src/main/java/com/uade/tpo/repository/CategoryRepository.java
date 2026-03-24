package com.uade.tpo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import com.uade.tpo.entity.Category;

public class CategoryRepository {
    public ArrayList<Category> categories = new ArrayList<Category>(
        Arrays.asList(
        Category.builder().description("relojes").id(1).build(),
        Category.builder().description("celulares").id(2).build(),
        Category.builder().description("notebooks").id(3).build())
    );


     public ArrayList<Category> getAllCategories() {
        return this.categories;
    }
    
    public String getCategoryById(int categoryId) {
        return null;
    }
   
    public String createCategory(String entity) {
        //TODO: process POST request
        
        return null;
    }
    
}

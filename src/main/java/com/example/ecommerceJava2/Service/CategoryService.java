package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public void saveCategory(Category category);
    public List<CategoryDTO> getAllCategories();
}

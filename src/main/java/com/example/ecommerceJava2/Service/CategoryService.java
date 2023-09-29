package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.CategoryDTO;
import com.example.ecommerceJava2.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    public void saveCategory(Category category);
    public List<CategoryDTO> getAllCategories();

    public void deleteCategory(int id) throws CategoryNotFoundException;

    public Category getCategory(Long id);

    public Category findCategoryById(Long id);
}

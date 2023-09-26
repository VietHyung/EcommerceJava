package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.CategoryDTO;
import com.example.ecommerceJava2.Repository.CategoryRepository;
import com.example.ecommerceJava2.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    private CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryRepository categoryRepository1) {
        this.categoryRepository = categoryRepository1;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category each : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(each.getCategoryId());
            categoryDTO.setName(each.getName());
            categoryDTO.setDescription(each.getDescription());
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }
}

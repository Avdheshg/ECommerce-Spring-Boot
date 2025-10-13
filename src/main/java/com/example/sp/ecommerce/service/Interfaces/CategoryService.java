package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.model.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    String createCategory(List<Category> category);
    String deleteCategoryById(Long id);
    String deleteCategoryByName(String name);
    Category updateCategory(Category category, Long categoryId);
}

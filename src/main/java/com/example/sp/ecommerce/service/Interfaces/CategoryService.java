package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.model.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();
    Category getCategory(Long categoryId);
    String createCategory(List<Category> category);
    String deleteCategory(Long id);
    String deleteCategory(String name);
    Category updateCategory(Category category, Long categoryId);
}

package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.payload.category.CategoryDTO;
import com.example.sp.ecommerce.payload.category.CategoryResponse;

import java.util.List;

public interface CategoryService
{
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryDTO getCategoryDTOById(Long categoryId);
    String createCategory(List<CategoryDTO> category);
    String deleteCategoryById(Long id);
    String deleteCategoryByName(String name);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryDTOId);
}

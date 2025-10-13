package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.exceptions.APIException;
import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.respositories.CategoryRepository;
import com.example.sp.ecommerce.service.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty())
        {
            throw new ResourceNotFoundException("No Categories present!!");
        }

        return categories;
    }

    @Override
    public Category getCategoryById(Long categoryId)
    {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        return optionalCategory
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public String createCategory(List<Category> categoryList) {
        categoryRepository.saveAll(categoryList);
        return "Category added successfully";
    }

    @Override
    public String deleteCategoryById(Long categoryId)
    {
        Category foundCategory = getCategoryById(categoryId);

        categoryRepository.delete(foundCategory);

        return "Category with ID " + categoryId + " deleted successfully";
    }

    @Override
    public String deleteCategoryByName(String name)
    {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Name", name));

        categoryRepository.delete(category);

        return "Item with the name " + name + " removed!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        Category foundCategory = getCategoryById(categoryId);

        foundCategory.setName(category.getName());
        return categoryRepository.save(foundCategory);
    }

}

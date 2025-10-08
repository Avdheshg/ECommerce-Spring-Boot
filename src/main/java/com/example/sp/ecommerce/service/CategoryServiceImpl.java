package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.respositories.CategoryRepository;
import com.example.sp.ecommerce.service.Interfaces.CategoryService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId)
    {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        return optionalCategory
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found for the given Id: " + categoryId));
    }

    @Override
    public String createCategory(List<Category> categoryList) {
        categoryRepository.saveAll(categoryList);
        return "Category added successfully";
    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        Category foundCategory = getCategory(categoryId);

        categoryRepository.delete(foundCategory);

        return "Category with ID " + categoryId + " deleted successfully";
    }

    @Override
    public String deleteCategory(String name)
    {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categoryRepository.delete(category);

        return "Item with the name " + name + " removed!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        Category foundCategory = getCategory(categoryId);

        foundCategory.setName(category.getName());
        return categoryRepository.save(foundCategory);
    }

}

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
    public String deleteCategory(Long id)
    {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categoryRepository.delete(category);
        return "Category with ID " + id + " deleted successfully";
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
        List<Category> categories = categoryRepository.findAll();

        Optional<Category> optionalCategory = categories.stream()
                .filter(cat -> cat.getId().equals(categoryId))
                .findFirst();

        if (optionalCategory.isPresent())
        {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, category.getName().toUpperCase() + " Not found!");
    }

}

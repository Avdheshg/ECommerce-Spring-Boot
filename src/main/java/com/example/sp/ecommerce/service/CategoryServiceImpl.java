package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private List<Category> categories = new ArrayList<Category>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(List<Category> categoryList) {
        for (Category category : categoryList)
        {
            category.setId(nextId++);
            categories.add(category);
        }
        return "Category added successfully";
    }

    @Override
    public String deleteCategory(Long id)
    {
        Category category = categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categories.remove(category);
        return "Category with ID " + id + " deleted successfully";
    }

    @Override
    public String deleteCategory(String name)
    {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categories.remove(category);

        return "Item with the name " + name + " removed!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        Optional<Category> optionalCategory = categories.stream()
                .filter(cat -> cat.getId().equals(categoryId))
                .findFirst();

        if (optionalCategory.isPresent())
        {
            Category cat = optionalCategory.get();
            cat.setName(category.getName());
            return cat;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, category.getName().toUpperCase() + " Not found!");
    }

}

package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.service.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController
{
    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/category")
    public ResponseEntity<List<Category>> getAllCategory()
    {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<String> createCategory(@RequestBody List<Category> category)
    {
        String result = categoryService.createCategory(category);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/byName/{categoryName}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryName)
    {
        try
        {
            String result = categoryService.deleteCategory(categoryName);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getReason(), responseStatusException.getStatusCode());
        }
    }

    @DeleteMapping("/admin/categories/byId/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable String categoryId)
    {
        try
        {
            String result = categoryService.deleteCategory(Long.parseLong(categoryId));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId)
    {
        try
        {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>(category.getName() + " saved successfully!", HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

}

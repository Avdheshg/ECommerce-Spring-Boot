package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.config.AppConstants;
import com.example.sp.ecommerce.payload.category.CategoryDTO;
import com.example.sp.ecommerce.payload.category.CategoryResponse;
import com.example.sp.ecommerce.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/public/category/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId)
    {
        CategoryDTO categoryDTO = categoryService.getCategoryDTOById(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategories(@RequestBody List<CategoryDTO> categories)
    {
        String result = categoryService.createCategories(categories);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO category)
    {
        List<CategoryDTO> list = new ArrayList<>();
        list.add(category);
        String result = categoryService.createCategories(list);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/byName/{categoryName}")
    public ResponseEntity<String> deleteCategoryByName(@PathVariable String categoryName)
    {
        String result = categoryService.deleteCategoryByName(categoryName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/byId/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId)
    {
        String result = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO category, @PathVariable Long categoryId)
    {
        CategoryDTO categoryDTO = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

}

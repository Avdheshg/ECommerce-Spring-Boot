package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.payload.category.CategoryDTO;
import com.example.sp.ecommerce.payload.category.CategoryResponse;
import com.example.sp.ecommerce.respositories.CategoryRepository;
import com.example.sp.ecommerce.service.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty())
        {
            throw new ResourceNotFoundException("No Categories present!!");
        }

        return getCategoryResponseFromCategories(categories);
    }

    public Category getCategoryById(Long categoryId)
    {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        return optionalCategory
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public CategoryDTO getCategoryDTOById(Long categoryId)
    {
        return getCategoryDTOFromCategory(getCategoryById(categoryId));
    }

    @Override
    public String createCategory(List<CategoryDTO> categoriesDTO) {
        List<Category> categories = getCategoriesFromCategoryDTO(categoriesDTO);

        categoryRepository.saveAll(categories);
        return "Category added successfully";
    }

    @Override
    public String deleteCategoryById(Long categoryId)
    {
        categoryRepository.deleteById(categoryId);

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
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryDTOId)
    {
        Category foundCategory = getCategoryById(categoryDTOId);

        foundCategory.setName(categoryDTO.getName());
        categoryRepository.save(foundCategory);
        return getCategoryDTOFromCategory(foundCategory);
    }

    private Category getCategoryFromDTO(CategoryDTO categoryDTO)
    {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private CategoryDTO getCategoryDTOFromCategory(Category category)
    {
        return new CategoryDTO(category.getId(), category.getName());
    }

    private List<Category> getCategoriesFromCategoryDTO(List<CategoryDTO> categoriesDTO)
    {
        return categoriesDTO.stream()
                .map(categoryDTO -> new Category(categoryDTO.getName()))
                .toList();
    }

    private CategoryResponse getCategoryResponseFromCategories(List<Category> categories)
    {
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setData(categories.stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .toList());

        categoryResponse.setTotalCategories(categories.size());

        return categoryResponse;
    }

    private CategoryResponse getCategoryResponseFromCategory(Category category)
    {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        return getCategoryResponseFromCategories(categories);
    }
}

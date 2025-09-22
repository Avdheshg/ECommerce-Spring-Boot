package com.example.sp.ecommerce.controller;

import java.util.List;

import com.example.sp.ecommerce.model.Category;

public class CategoryResponse
{
    private int numberOfCategories;
    private List<Category> categories;

    public CategoryResponse(int numberOfCategories, List<Category> categories)
    {
        this.numberOfCategories = numberOfCategories;
        this.categories = categories;
    }

    public int getNumberOfCategories() {
        return numberOfCategories;
    }

    public List<Category> getCategories() {
        return categories;
    }
}

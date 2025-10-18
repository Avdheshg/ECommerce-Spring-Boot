package com.example.sp.ecommerce.payload.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse
{
    private int totalCategories;

    private List<CategoryDTO> data;

}

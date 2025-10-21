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
    private long totalCategories;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean isLastPage;
    private List<CategoryDTO> data;
}

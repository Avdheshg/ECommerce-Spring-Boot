package com.example.sp.ecommerce.helpers;

import com.example.sp.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationHelper
{
    public static Pageable buildPageable(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, List<String> allowedFieldsForSorting)
    {
        Sort sort = SortingHelper.getValidSort(sortBy, sortDir, allowedFieldsForSorting);
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}

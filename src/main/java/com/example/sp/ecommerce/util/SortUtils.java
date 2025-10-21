package com.example.sp.ecommerce.util;

import com.example.sp.ecommerce.config.AppConstants;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SortUtils
{
    public static String validateSortField(String sortBy, List<String> allowedField)
    {
        return allowedField.contains(sortBy) ? sortBy : AppConstants.SORT_BY;
    }

    public static String validateSortDirection(String sortDirection)
    {
        return sortDirection.equalsIgnoreCase("desc") ? "desc" : AppConstants.SORT_DIR;
    }

    public static Sort getValidSort(String sortBy, String sortDirection, List<String> allowedSortField)
    {
        sortDirection = validateSortDirection(sortDirection);
        sortBy = validateSortField(sortBy, allowedSortField);

        return sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
    }
}

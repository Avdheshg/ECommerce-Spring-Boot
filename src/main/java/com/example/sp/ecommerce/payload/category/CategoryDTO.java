package com.example.sp.ecommerce.payload.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO
{
    private Long id;

    @NotEmpty
    private String name;


}



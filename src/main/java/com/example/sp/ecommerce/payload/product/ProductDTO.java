package com.example.sp.ecommerce.payload.product;

import com.example.sp.ecommerce.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO
{
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long categoryId;

    @NotNull
    private Integer stockQuantity;

    public ProductDTO(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.categoryId = product.getCategoryId();
        this.stockQuantity = product.getStockQuantity();
    }
}

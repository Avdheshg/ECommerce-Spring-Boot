package com.example.sp.ecommerce.payload.product;

import com.example.sp.ecommerce.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive
    private Double amount;

    @NotNull
    private Long categoryId;

    @NotNull
    private Integer stockQuantity;

    public ProductDTO(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.categoryId = product.getCategoryId();
        this.stockQuantity = product.getStockQuantity();
    }
}

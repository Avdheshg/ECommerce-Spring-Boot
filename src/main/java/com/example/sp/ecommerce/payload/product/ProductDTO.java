package com.example.sp.ecommerce.payload.product;

import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.model.Product;
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
    private Double price;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String description;
    private double discount;
    @NotBlank
    private String image;

    private double specialPrice;

    private transient Category category;
//    @NotNull
//    private Long sellerId;

    public ProductDTO(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.discount = product.getDiscount();
        this.image = product.getImage();
        this.specialPrice = product.getSpecialPrice();
        this.category = product.getCategory();
    }
}




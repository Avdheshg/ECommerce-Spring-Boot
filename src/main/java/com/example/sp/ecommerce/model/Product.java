package com.example.sp.ecommerce.model;

import com.example.sp.ecommerce.payload.product.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String description;
    private Double discount;
    @NotBlank
    private String image;

    private double specialPrice;
//    @NotNull
//    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductDTO productDTO)
    {
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.quantity = productDTO.getQuantity();
        this.description = productDTO.getDescription();
        this.discount = productDTO.getDiscount();
        this.image = productDTO.getImage();
        this.specialPrice = productDTO.getSpecialPrice();
    }

}

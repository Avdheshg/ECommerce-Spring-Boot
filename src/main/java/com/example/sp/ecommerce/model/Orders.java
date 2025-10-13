package com.example.sp.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Integer productQuantity;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String shippingAddress;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    @Size(min = 2, max = 100, message = "Name should have at least 2 characters")
    private String customerName;

}

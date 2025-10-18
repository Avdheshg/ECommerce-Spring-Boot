package com.example.sp.ecommerce.model;

import com.example.sp.ecommerce.payload.order.OrderDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order
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

    private LocalDateTime createdAt;

    @NotBlank
    @Size(min = 2, max = 100, message = "Name should have at least 2 characters")
    private String customerName;

    public Order(OrderDTO orderDTO)
    {
        this.id = orderDTO.getId();
        this.productId = orderDTO.getProductId();
        this.productQuantity = orderDTO.getProductQuantity();
        this.amount = orderDTO.getAmount();
        this.shippingAddress = orderDTO.getShippingAddress();
        this.createdAt = LocalDateTime.now();
        this.customerName = orderDTO.getCustomerName();
    }

}



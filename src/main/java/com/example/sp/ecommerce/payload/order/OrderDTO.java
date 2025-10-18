package com.example.sp.ecommerce.payload.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.sp.ecommerce.model.Order;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO
{
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


    public OrderDTO(Order order)
    {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.productQuantity = order.getProductQuantity();
        this.amount = order.getAmount();
        this.shippingAddress = order.getShippingAddress();
        this.createdAt = LocalDateTime.now();
        this.customerName = order.getCustomerName();
    }
}

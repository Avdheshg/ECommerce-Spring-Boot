package com.example.sp.ecommerce.payload.product;

import com.example.sp.ecommerce.model.Product;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse
{
    private int totalOrders;

    private List<ProductDTO> productDTOs = new ArrayList<>();

}

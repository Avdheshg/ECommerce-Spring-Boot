package com.example.sp.ecommerce.payload.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse
{
    private int totalOrders;
    private List<OrderDTO> orderDTOList;
}

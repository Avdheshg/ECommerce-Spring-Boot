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
    private long totalOrders;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean isLastPage;
    private List<OrderDTO> orderDTOList;
}

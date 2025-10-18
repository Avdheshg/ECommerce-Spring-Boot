package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.payload.order.OrderDTO;
import com.example.sp.ecommerce.payload.order.OrderResponse;

import java.util.List;

public interface OrderService
{
    String createOrders(List<OrderDTO> orderDTOs);
    OrderDTO updateOrder(OrderDTO orderDTO, Long orderId);

    OrderResponse getAllOrders();
    OrderDTO getOrderDTOById(Long orderId);

    String deleteOrder(Long orderId);
}

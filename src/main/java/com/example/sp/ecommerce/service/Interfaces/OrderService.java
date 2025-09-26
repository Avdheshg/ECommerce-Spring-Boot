package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.model.Orders;

import java.util.List;

public interface OrderService
{
    String createOrders(List<Orders> orders);
    String updateOrder(Orders orders, Long orderId);

    List<Orders> getAllOrders();
    Orders getOrder(Long orderId);

    String deleteOrder(Long orderId);
}

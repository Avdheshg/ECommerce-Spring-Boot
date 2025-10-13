package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.respositories.OrderRepository;
import com.example.sp.ecommerce.service.Interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.sp.ecommerce.model.Orders;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createOrders(List<Orders> orders)
    {
        orderRepository.saveAll(orders);
        return "Orders created!";
    }

    @Override
    public List<Orders> getAllOrders()
    {
        List<Orders> orders =  orderRepository.findAll();

        if (orders.isEmpty())
        {
            throw new ResourceNotFoundException("No Orders present!!");
        }

        return orders;
    }

    @Override
    public Orders getOrderById(Long orderId)
    {
        Optional<Orders> optionalOrder =  orderRepository.findById(orderId);

        return optionalOrder
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

    }

    @Override
    public String updateOrder(Orders orders, Long orderId)
    {
        Orders foundOrders = getOrderById(orderId);

        if (orders.getCustomerName().isBlank() == false)
        {
            foundOrders.setCustomerName(orders.getCustomerName());
        }

        orderRepository.save(foundOrders);
        return "Order updated successfully!";
    }

    @Override
    public String deleteOrder(Long orderId)
    {
        Orders foundOrders = getOrderById(orderId);

        orderRepository.delete(foundOrders);

        return "Order with Id: " + orderId + " deleted successfully!";
    }



}

package com.example.sp.ecommerce.service;

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
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrder(Long orderId)
    {
        Optional<Orders> optionalOrder =  orderRepository.findById(orderId);

        Orders foundOrder = optionalOrder
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order found with the given Id: " + orderId));

        return foundOrder;
    }

    @Override
    public String updateOrder(Orders orders, Long orderId)
    {
        Orders foundOrders = getOrder(orderId);

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
        orderRepository.deleteById(orderId);

        return "Order with Id: " + orderId + " deleted successfully!";
    }



}

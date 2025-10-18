package com.example.sp.ecommerce.service;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.payload.order.OrderDTO;
import com.example.sp.ecommerce.payload.order.OrderResponse;
import com.example.sp.ecommerce.respositories.OrderRepository;
import com.example.sp.ecommerce.service.Interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.sp.ecommerce.model.Order;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createOrders(List<OrderDTO> orderDTOs)
    {
        orderRepository.saveAll(getOrdersFromDTOs(orderDTOs));
        return "Orders created!";
    }

    @Override
    public OrderResponse getAllOrders()
    {
        List<Order> orders =  orderRepository.findAll();

        if (orders.isEmpty())
        {
            throw new ResourceNotFoundException("No Orders present!!");
        }

        return getOrderResponseFromOrders(orders);
    }

    private Order getOrderById(Long orderId)
    {
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        return foundOrder
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

    }

    @Override
    public OrderDTO getOrderDTOById(Long orderId)
    {
        return new OrderDTO(getOrderById(orderId));
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO, Long orderId)
    {
        Order foundOrder = getOrderById(orderId);

        if (orderDTO.getCustomerName().isBlank() == false)
        {
            foundOrder.setCustomerName(orderDTO.getCustomerName());
        }

        return new OrderDTO(orderRepository.save(foundOrder));
    }

    @Override
    public String deleteOrder(Long orderId)
    {
        orderRepository.deleteById(orderId);

        return "Order with Id: " + orderId + " deleted successfully!";
    }

    private OrderResponse getOrderResponseFromOrders(List<Order> orders)
    {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        orderDTOs = orders.stream()
                .map(order -> new OrderDTO(order))
                .toList();

        return new OrderResponse(orders.size(), orderDTOs);
    }

    private List<Order> getOrdersFromDTOs(List<OrderDTO> orderDTOs)
    {
        return orderDTOs.stream()
                .map(orderDTO -> new Order(orderDTO))
                .toList();

    }

}

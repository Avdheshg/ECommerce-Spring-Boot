package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.model.Orders;
import com.example.sp.ecommerce.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController
{
    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/public/orders")
    public ResponseEntity<String> createOrders(@RequestBody List<Orders> orders)
    {
        System.out.println("Inside Create Orders...");
        return new ResponseEntity<>(orderService.createOrders(orders), HttpStatus.CREATED);
    }

    @PatchMapping("/public/orders/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody Orders orders, @PathVariable Long orderId)
    {
        try
        {
            return new ResponseEntity<>(orderService.updateOrder(orders, orderId), HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

    @GetMapping("/public/orders")
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @DeleteMapping("/public/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId)
    {
        try
        {
            return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.NO_CONTENT);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }


}

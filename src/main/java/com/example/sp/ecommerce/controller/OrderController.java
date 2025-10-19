package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.model.Order;
import com.example.sp.ecommerce.payload.order.OrderDTO;
import com.example.sp.ecommerce.payload.order.OrderResponse;
import com.example.sp.ecommerce.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<String> createOrders(@Valid @RequestBody List<OrderDTO> orderDTOs)
    {
        return new ResponseEntity<>(orderService.createOrders(orderDTOs), HttpStatus.CREATED);
    }

    @PostMapping("/public/order")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDTO orderDTO)
    {
        List<OrderDTO> list = new ArrayList<>();
        list.add(orderDTO);
        return new ResponseEntity<>(orderService.createOrders(list), HttpStatus.CREATED);
    }

    @GetMapping("/public/orders")
    public ResponseEntity<OrderResponse> getAllOrders(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        int number = pageNumber == null ? 0 : pageNumber;
        int size = pageSize == null ? 10 : pageSize;

        return new ResponseEntity<>(orderService.getAllOrders(number, size), HttpStatus.OK);
    }

    @GetMapping("/public/order/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId)
    {
        return new ResponseEntity<>(orderService.getOrderDTOById(orderId), HttpStatus.OK);
    }

    @PatchMapping("/public/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable Long orderId)
    {
        return new ResponseEntity<>(orderService.updateOrder(orderDTO, orderId), HttpStatus.OK);
    }

    @DeleteMapping("/public/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId)
    {
        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.NO_CONTENT);
    }


}

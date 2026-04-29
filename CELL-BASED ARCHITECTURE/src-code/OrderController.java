package com.example.order_service.controller;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // CREATE order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // GET all orders
    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
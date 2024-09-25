package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")

public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody Order order) {
        Double total = order.getProducts().stream().mapToDouble(Product::getPrice).sum();
        order.setTotal(total);
        return this.orderService.addOrder(order);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") String id) {
        return this.orderService.deleteOrder(id);
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody Order order) {
        Double total = order.getProducts().stream().mapToDouble(Product::getPrice).sum();
        order.setTotal(total);
        return this.orderService.updateOrder(order);
    }
}

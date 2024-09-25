package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Object> addOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }


    public List<Order> getOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            Logger.getLogger(OrderService.class.getName()).severe(e.getMessage());
            return Collections.emptyList();
        }

    }

    // deleteOrder method

    public ResponseEntity<Object> deleteOrder(String id) {
        try {
            orderRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HashMap<String, String>() {{
                put("message", "Order deleted successfully");
            }});
        } catch (Exception e) {
            Logger.getLogger(OrderService.class.getName()).severe(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{
                put("message", "Error deleting order");
            }});
        }
    }

    /// updateOrder method
    public ResponseEntity<Object> updateOrder(Order order) {
        try {
            Order existingOrder = orderRepository.findById(order.getId()).orElse(null);
            if (existingOrder == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                    put("message", "Order not found");
                }});
            }
            order.setId(order.getId());
            Order updatedOrder = orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
        } catch (Exception e) {
            Logger.getLogger(OrderService.class.getName()).severe(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{
                put("message", "Error updating order");
            }});
        }
    }

}

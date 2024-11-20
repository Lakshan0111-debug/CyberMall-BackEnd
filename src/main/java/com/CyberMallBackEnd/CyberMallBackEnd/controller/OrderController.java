package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.OrderDto;
import com.CyberMallBackEnd.CyberMallBackEnd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to get all orders
    @GetMapping("/get-all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Endpoint to search for an order by its ID
    @GetMapping("/search-order/{orderId}")
    public OrderDto searchOrderById(@PathVariable Long orderId) {
        return orderService.searchOrderById(orderId);
    }

    // Endpoint to add a new order
    @PostMapping("/add-order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return ResponseEntity.ok("Order saved successfully!");
    }

    // Endpoint to update an existing order
    @PutMapping("/update-order/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        boolean isUpdated;
        isUpdated = orderService.updateOrder(orderId, orderDto);
        if (isUpdated) {
            return ResponseEntity.ok("Order updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If order with the given ID is not found
        }
    }

    // Endpoint to delete an order
    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        boolean isDeleted = orderService.deleteOrder(orderId);
        if (isDeleted) {
            return ResponseEntity.ok("Order deleted successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If order with the given ID is not found
        }
    }
}

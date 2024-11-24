package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.OrderDto;
import com.CyberMallBackEnd.CyberMallBackEnd.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    // Endpoint to get all orders
    @GetMapping("/get-all")
    public List<OrderDto> getAllOrders() {
        return orderServiceImpl.getAllOrders();
    }

    // Endpoint to search for an order by its ID
    @GetMapping("/search-order/{orderId}")
    public OrderDto searchOrderById(@PathVariable Long orderId) {
        return orderServiceImpl.searchOrderById(orderId);
    }

    // Endpoint to add a new order
    @PostMapping("/add-order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto orderDto) {
        orderServiceImpl.saveOrder(orderDto);
        return ResponseEntity.ok("Order saved successfully!");
    }


    // Endpoint to update an existing order
    @PutMapping("/update-order/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        boolean isUpdated;
        isUpdated = orderServiceImpl.updateOrder(orderId, orderDto);
        if (isUpdated) {
            return ResponseEntity.ok("Order updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If order with the given ID is not found
        }
    }

    // Endpoint to delete an order
    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        boolean isDeleted = orderServiceImpl.deleteOrder(orderId);
        if (isDeleted) {
            return ResponseEntity.ok("Order deleted successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If order with the given ID is not found
        }
    }
}

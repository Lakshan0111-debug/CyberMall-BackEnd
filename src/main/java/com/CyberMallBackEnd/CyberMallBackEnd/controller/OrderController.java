package com.CyberMallBackEnd.CyberMallBackEnd.controller;


import com.CyberMallBackEnd.CyberMallBackEnd.dto.OrderDto;
import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/search-order/{orderId}")
    public OrderDto searchOrderById(@PathVariable Long orderId){
        return orderService.searchOrderById(orderId);
    }

    @PostMapping("/add-order")
    public ResponseEntity<String> saveOrder(@RequestBody @RequestParam("customer") String customerName,
                                              @RequestParam("address") String shippingAddress,
                                              @RequestParam("email") String email,
                                              @RequestParam("phone") String phoneNo,
                                              @RequestParam("total_price") String totalPrice,
                                              @RequestParam("no_of_items") String noOfItems,
                                              @RequestParam("date") String date,
                                              @RequestParam("time") String time) {

        try {
            orderService.saveOrder(new OrderDto(customerName, shippingAddress, email, phoneNo, totalPrice, noOfItems, date, time));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Order saved successfully!");
    }

    // PUT method for updating an existing product
    @PutMapping("/update-order/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody @PathVariable Long orderId,
                                              @RequestParam("customer") String customerName,
                                              @RequestParam("address") String shippingAddress,
                                              @RequestParam("email") String email,
                                              @RequestParam("phone") String phoneNo,
                                              @RequestParam("total_price") String totalPrice,
                                              @RequestParam("no_of_items") String noOfItems,
                                              @RequestParam("date") String date,
                                              @RequestParam("time") String time) {
        boolean isUpdated = false;
        try {
            isUpdated = orderService.updateOrder(orderId ,new OrderDto(customerName, shippingAddress, email, phoneNo, totalPrice, noOfItems, date, time));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (isUpdated) {
            return ResponseEntity.ok("Order updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If order with the given ID is not found
        }
    }

    @DeleteMapping("delete-order/{orderId}")
    public boolean deleteOrder(@PathVariable Long orderId){
        return orderService.deleteOrder(orderId);
    }

}
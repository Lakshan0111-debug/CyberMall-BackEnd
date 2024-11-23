package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import com.CyberMallBackEnd.CyberMallBackEnd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllcustomers() {
        return customerService.getAllcustomers();
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.getcustomerById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addC")
    public ResponseEntity<String> savecustomer(@RequestBody Customer customer) {
        customerService.savecustomer(customer.getCustomerName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getUserName(),customer.getPassword());
        return ResponseEntity.ok("customer saved successfully!");
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updatecustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        boolean isUpdated = customerService.updatecustomer(customerId, customer.getCustomerName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress());
        if (isUpdated) {
            return ResponseEntity.ok("customer updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If customer with the given ID is not found
        }
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deletecustomer(@PathVariable Long customerId) {
        customerService.deletecustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}

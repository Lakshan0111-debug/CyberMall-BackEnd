package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CustomerDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import com.CyberMallBackEnd.CyberMallBackEnd.service.impl.CustomerServiceImpl;
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
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping
    public List<CustomerDto> getAllcustomers() {
        return customerServiceImpl.getAllcustomers();
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer customerId) {
        Optional<CustomerDto> customer = customerServiceImpl.getcustomerById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addC")
    public ResponseEntity<String> savecustomer(@RequestBody Customer customer) {
        customerServiceImpl.savecustomer(customer.getCustomerName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getUserName(),customer.getPassword());
        return ResponseEntity.ok("customer saved successfully!");
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updatecustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
        boolean isUpdated = customerServiceImpl.updatecustomer(customerId, customer.getCustomerName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress());
        if (isUpdated) {
            return ResponseEntity.ok("customer updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If customer with the given ID is not found
        }
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deletecustomer(@PathVariable Integer customerId) {
        customerServiceImpl.deletecustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-by-email/{email}")
    public CustomerDto findByEmail(@PathVariable String email){

        return customerServiceImpl.findByEmail(email);
    }
}

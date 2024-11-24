package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer savecustomer(String customerName, String email, String phoneNumber, String address, String userName, String password);
    List<Customer> getAllcustomers();
    Optional<Customer> getcustomerById(Integer customerId);
    boolean updatecustomer(Integer customerId, String customerName, String email, String phoneNumber, String address);
    void deletecustomer(Integer customerId);
}

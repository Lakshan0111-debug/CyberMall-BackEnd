package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CustomerDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDto savecustomer(String customerName, String email, String phoneNumber, String address, String userName, String password);
    List<CustomerDto> getAllcustomers();
    Optional<CustomerDto> getcustomerById(Integer customerId);
    boolean updatecustomer(Integer customerId, String customerName, String email, String phoneNumber, String address);
    void deletecustomer(Integer customerId);

    CustomerDto findByEmail(String email);

}

package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.CustomerRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;



    public List<Customer> getAllcustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getcustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer savecustomer( String customerName, String email,String phoneNumber, String address, String userName, String password) {
        Customer customer = new Customer();


        customer.setCustomerName(customerName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setUserName(userName);
        customer.setPassword(password);

        return customerRepository.save(customer);
    }
    public boolean updatecustomer(Integer customerId, String customerName, String email, String phoneNumber, String address) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerName);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customerRepository.save(customer);
            return true;
        } else {
            return false;
        }
    }

    public void deletecustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}

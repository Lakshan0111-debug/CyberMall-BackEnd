package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CustomerDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.CustomerRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    ModelMapper mapper;



    public List<CustomerDto> getAllcustomers() {
        List<CustomerDto> customers=new ArrayList<>();
        for(Customer customer:customerRepository.findAll()){
            customers.add(mapper.map(customer,CustomerDto.class));
        }
        return customers;
    }

    public Optional<CustomerDto> getcustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        if(customer!=null){
            return Optional.ofNullable(mapper.map(customer, CustomerDto.class));
        }
        return null;
    }

    public CustomerDto savecustomer( String customerName, String email,String phoneNumber, String address, String userName, String password) {
        Customer customer = new Customer();


        customer.setCustomerName(customerName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setUserName(userName);
        customer.setPassword(password);

        return mapper.map(customerRepository.save(customer),CustomerDto.class);
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

    @Override
    public CustomerDto findByEmail(String email) {
       for(CustomerDto customerDto:getAllcustomers()){
            if(customerDto.getEmail().equals(email)){
                return customerDto;
            }
        }

        return null;
    }
}

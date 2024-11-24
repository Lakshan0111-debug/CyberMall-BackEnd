package com.CyberMallBackEnd.CyberMallBackEnd.repository;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer,Integer> {


}

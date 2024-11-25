package com.CyberMallBackEnd.CyberMallBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
}



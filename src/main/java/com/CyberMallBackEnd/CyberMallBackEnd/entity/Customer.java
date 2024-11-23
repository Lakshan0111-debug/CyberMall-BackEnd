package com.CyberMallBackEnd.CyberMallBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customer")
@Data // Lombok annotation
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
}



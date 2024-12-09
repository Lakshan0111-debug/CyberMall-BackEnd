package com.CyberMallBackEnd.CyberMallBackEnd.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;


    private String customerName;
    private String phoneNumber;
    private String address;
    private String nic;


}

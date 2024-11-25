package com.CyberMallBackEnd.CyberMallBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;


}

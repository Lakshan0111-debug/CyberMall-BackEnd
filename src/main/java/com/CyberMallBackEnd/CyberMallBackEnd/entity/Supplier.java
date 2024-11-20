package com.CyberMallBackEnd.CyberMallBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long SupplierId;

    @Column(name = "supplier_name")
    private String SupplierName;

    @Column(name = "email")
    private String Email;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "address")
    private String Address;


}

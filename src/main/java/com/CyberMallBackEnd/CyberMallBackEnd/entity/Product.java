package com.CyberMallBackEnd.CyberMallBackEnd.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "unit_price")
    private String unitPrice;

    @Column(name = "quantity")
    private String quantity;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
}

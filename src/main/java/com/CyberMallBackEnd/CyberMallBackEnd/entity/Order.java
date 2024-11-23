package com.CyberMallBackEnd.CyberMallBackEnd.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "customer")
    private String customerName;

    @Column(name = "address")
    private String shippingAddress;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNo;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "no_of_items")
    private String noOfItems;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;
}

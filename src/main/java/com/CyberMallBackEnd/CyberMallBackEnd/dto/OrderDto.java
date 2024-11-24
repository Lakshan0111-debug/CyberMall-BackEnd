package com.CyberMallBackEnd.CyberMallBackEnd.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private Integer orderId;
    private String customerName;
    private String shippingAddress;
    private String email;
    private String phoneNo;
    private String totalPrice;
    private String noOfItems;
    private String date;
    private String time;
}

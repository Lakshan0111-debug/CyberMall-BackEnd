package com.CyberMallBackEnd.CyberMallBackEnd.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

    private String productName;
    private String description;
    private String supplierName;
    private String unitPrice;
    private String quantity;
    private byte[] image;
}
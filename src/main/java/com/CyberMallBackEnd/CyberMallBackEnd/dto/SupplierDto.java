package com.CyberMallBackEnd.CyberMallBackEnd.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDto {
    private Long supplierId;


    private String supplierName;


    private String email;


    private String phoneNumber;


    private String address;
}

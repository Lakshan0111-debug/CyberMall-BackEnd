package com.CyberMallBackEnd.CyberMallBackEnd.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private Integer id;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
}

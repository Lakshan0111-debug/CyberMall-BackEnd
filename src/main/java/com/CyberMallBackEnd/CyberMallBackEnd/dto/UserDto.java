package com.CyberMallBackEnd.CyberMallBackEnd.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;

    private String email;

    private String userName;

    private String password;


    private String customerName;
    private String phoneNumber;
    private String address;
    private String nic;
}

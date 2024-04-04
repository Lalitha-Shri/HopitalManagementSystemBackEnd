package com.lalitha.hospitalmanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//dto for registration form
public class RegisterDto {

    private  String name;

    private String username;

    private  String email;

    private  String password;

}

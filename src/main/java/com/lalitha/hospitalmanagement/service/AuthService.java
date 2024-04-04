package com.lalitha.hospitalmanagement.service;


import com.lalitha.hospitalmanagement.dto.JwtAuthResponse;
import com.lalitha.hospitalmanagement.dto.LoginDto;
import com.lalitha.hospitalmanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);


    JwtAuthResponse login(LoginDto loginDto);
}

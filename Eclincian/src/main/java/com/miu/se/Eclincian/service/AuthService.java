package com.miu.se.Eclincian.service;


import com.miu.se.Eclincian.entity.dto.request.LoginRequest;
import com.miu.se.Eclincian.entity.dto.request.RegisterRequest;
import com.miu.se.Eclincian.entity.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
}

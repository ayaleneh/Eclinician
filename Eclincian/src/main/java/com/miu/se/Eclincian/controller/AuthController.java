package com.miu.se.Eclincian.controller;
//import cs544.ea.OnlineRetailSystem.domain.dto.request.LoginRequest;
//import cs544.ea.OnlineRetailSystem.domain.dto.request.RegisterRequest;
//import cs544.ea.OnlineRetailSystem.domain.dto.response.LoginResponse;
//import cs544.ea.OnlineRetailSystem.service.AuthService;
import com.miu.se.Eclincian.entity.dto.request.LoginRequest;
import com.miu.se.Eclincian.entity.dto.request.RegisterRequest;
import com.miu.se.Eclincian.entity.dto.response.LoginResponse;
import com.miu.se.Eclincian.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
         try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
        }
    }



}

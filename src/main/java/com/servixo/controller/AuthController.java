package com.servixo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servixo.dto.LoginDto;
import com.servixo.dto.auth.RegisterRequest;
import com.servixo.entity.User;
import com.servixo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRoleName()
        ));
    }
    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody LoginDto dto) {
        return authService.login(dto.getEmail(), dto.getPassword());
    }
}
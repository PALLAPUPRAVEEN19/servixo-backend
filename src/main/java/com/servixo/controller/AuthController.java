package com.servixo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servixo.dto.EmailRequest;
import com.servixo.dto.LoginDto;
import com.servixo.dto.VerifyOtpRequest;
import com.servixo.dto.auth.RegisterRequest;
import com.servixo.entity.User;
import com.servixo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRoleName()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

    // ================= LOGIN (STEP 1) =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        Map<String, Object> response = authService.loginAndSendOtp(
                dto.getEmail(),
                dto.getPassword()
        );

        return ResponseEntity.ok(response);
    }

    // ================= VERIFY LOGIN OTP (STEP 2) =================
    @PostMapping("/verify-login-otp")
    public ResponseEntity<?> verifyLoginOtp(@RequestBody VerifyOtpRequest request) {

        User user = authService.verifyLoginOtp(
                request.getEmail(),
                request.getOtp()
        );

        return ResponseEntity.ok(user);
    }

    // ================= OPTIONAL (RESEND OTP) =================
    @PostMapping("/send-otp")
    public ResponseEntity<?> resendOtp(@RequestBody EmailRequest request) {

        authService.sendOtp(request.getEmail());

        return ResponseEntity.ok("OTP sent successfully");
    }
}
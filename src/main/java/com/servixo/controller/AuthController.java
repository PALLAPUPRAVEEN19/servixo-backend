package com.servixo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private org.springframework.core.env.Environment env;

    @GetMapping("/test-mail-config")
    public String testMailConfig() {
        return env.getProperty("spring.mail.username");
    }
    // ================= 🔥 REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRoleName()
        );

        // 🔥 After register → send OTP
        authService.sendOtp(request.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered. OTP sent to email.");
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

    // ================= 🔥 LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        User user = authService.login(dto.getEmail(), dto.getPassword());

        // 🔥 If NOT VERIFIED → send OTP again
        if (!user.isVerified()) {

            authService.sendOtp(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "NOT_VERIFIED");
            response.put("message", "OTP sent to your email");
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);
        }

        // ✅ SUCCESS LOGIN
        return ResponseEntity.ok(user);
    }

    // ================= 🔥 SEND OTP =================
 
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody EmailRequest request) {
        authService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
        authService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok("Email verified successfully");
    }
}
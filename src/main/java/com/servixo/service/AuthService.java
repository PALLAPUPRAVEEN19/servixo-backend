package com.servixo.service;

import com.servixo.entity.User;
import com.servixo.entity.Role;
import com.servixo.repository.UserRepository;
import com.servixo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    // ================= REGISTER =================
    public User register(String name, String email, String password, String roleName) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        Role role = roleRepository.findByName(
                (roleName == null || roleName.isEmpty()) ? "USER" : roleName
        ).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setVerified(false);

        return userRepository.save(user);
    }

    // ================= LOGIN =================
    public User login(String email, String password) {

        User user = userRepository.findByEmailWithRole(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // ================= SEND OTP =================
    public void sendOtp(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        emailService.sendEmail(
                email,
                "Servixo OTP Verification",
                "Your OTP is: " + otp + "\nValid for 5 minutes."
        );

        System.out.println("🔥 OTP SENT: " + otp);
    }

    // ================= VERIFY OTP =================
    public void verifyOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        user.setVerified(true);
        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);
    }
}
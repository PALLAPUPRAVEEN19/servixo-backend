package com.servixo.service;

import com.servixo.entity.User;
import com.servixo.entity.Role;
import com.servixo.repository.UserRepository;
import com.servixo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // ================= REGISTER =================
    public User register(String name, String email, String password, String roleName) {

        // ✅ Check if user already exists
        Optional<User> existing = userRepository.findByEmail(email);
        if (existing.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // ✅ Assign selected role or default to USER
        String targetRole = (roleName == null || roleName.trim().isEmpty()) ? "USER" : roleName;
        Role role = roleRepository.findByName(targetRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // ✅ Create user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // later encrypt
        user.setRole(role);

        return userRepository.save(user);
    }
    // ================= LOGIN =================
    public User login(String email, String password) {

        // 🔥 FIXED: use JOIN FETCH method
        User user = userRepository.findByEmailWithRole(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
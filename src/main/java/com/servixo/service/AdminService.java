package com.servixo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servixo.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, Long> getStats() {
        long users = userRepository.countByRole_Name("USER");
        long professionals = userRepository.countByRole_Name("PROFESSIONAL");

        Map<String, Long> stats = new HashMap<>();
        stats.put("users", users);
        stats.put("professionals", professionals);

        return stats;
    }
}
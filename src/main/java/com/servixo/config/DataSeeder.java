package com.servixo.config;

import com.servixo.entity.Role;
import com.servixo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        java.util.Map<String, String> rolesMap = new java.util.HashMap<>();
        rolesMap.put("ADMIN", "Manage platform settings, user roles, and service listings.");
        rolesMap.put("PROFESSIONAL", "Create and manage profiles, list services, and interact with clients.");
        rolesMap.put("USER", "Search for professionals, hire services, and provide feedback.");
        rolesMap.put("SUPPORT", "Assist users with inquiries, resolve issues, and ensure a smooth experience.");

        for (java.util.Map.Entry<String, String> entry : rolesMap.entrySet()) {
            String roleName = entry.getKey();
            String description = entry.getValue();
            
            Optional<Role> existingRole = roleRepository.findByName(roleName);
            if (existingRole.isEmpty()) {
                roleRepository.save(new Role(roleName, description));
                System.out.println("Seeded role: " + roleName);
            } else {
                Role existing = existingRole.get();
                if (existing.getDescription() == null || existing.getDescription().isEmpty()) {
                    existing.setDescription(description);
                    roleRepository.save(existing);
                    System.out.println("Updated description for role: " + roleName);
                }
            }
        }
    }
}

package com.servixo.service;

import com.servixo.entity.ServiceEntity;
import com.servixo.entity.User;
import com.servixo.repository.ServiceRepository;
import com.servixo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 CREATE SERVICE (Professional)
    public ServiceEntity createService(Long professionalId, ServiceEntity service) {

        User user = userRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + professionalId));

        // ✅ OPTIONAL: Ensure only PROFESSIONAL can create
        if (!user.getRole().getName().equalsIgnoreCase("PROFESSIONAL")) {
            throw new RuntimeException("Only professionals can create services");
        }

        service.setProfessional(user);   // ✅ LINK SERVICE TO USER
        service.setStatus("PENDING");    // ✅ DEFAULT STATUS

        return serviceRepository.save(service);
    }

    // 🔹 ADMIN - GET ALL SERVICES
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    // 🔹 ADMIN - APPROVE SERVICE
    public ServiceEntity approveService(Long id) {

        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));

        service.setStatus("APPROVED");

        return serviceRepository.save(service);
    }

    // 🔹 USER - GET ONLY APPROVED SERVICES
    public List<ServiceEntity> getApprovedServices() {
        return serviceRepository.findByStatus("APPROVED");
    }

    // 🔹 PROFESSIONAL - GET OWN SERVICES
    public List<ServiceEntity> getServicesByProfessional(Long professionalId) {

        // Optional check (good practice)
        if (!userRepository.existsById(professionalId)) {
            throw new RuntimeException("Professional not found with id: " + professionalId);
        }

        return serviceRepository.findByProfessional_Id(professionalId);
    }
 // 🔹 SEARCH SERVICES
    public List<ServiceEntity> searchServices(String keyword) {
        return serviceRepository
            .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
    // 🔹 DELETE SERVICE
    public void deleteService(Long id) {

        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found with id: " + id);
        }

        serviceRepository.deleteById(id);
    }
}
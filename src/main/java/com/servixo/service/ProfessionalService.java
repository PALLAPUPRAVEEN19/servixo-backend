package com.servixo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servixo.entity.ServiceEntity;
import com.servixo.entity.User;
import com.servixo.repository.ServiceRepository;
import com.servixo.repository.UserRepository;

@Service
public class ProfessionalService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= GET PROFESSIONAL (USER) =================
    public User getProfessionalById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional not found with ID: " + id));
    }

    // ================= ADD SERVICE =================
    public ServiceEntity createService(Long professionalId, ServiceEntity service) {

        User user = userRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Link service to professional (User)
        service.setProfessional(user);

        // Default status
        service.setStatus("PENDING");

        return serviceRepository.save(service);
    }

    // ================= GET SERVICES BY PROFESSIONAL =================
    public List<ServiceEntity> getServicesByProfessional(Long professionalId) {

        return serviceRepository.findByProfessional_Id(professionalId);
    }

    // ================= GET ALL APPROVED SERVICES =================
    public List<ServiceEntity> getApprovedServices() {

        return serviceRepository.findByStatus("APPROVED");
    }

    // ================= SEARCH SERVICES =================
    public List<ServiceEntity> searchServices(String keyword) {

        return serviceRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    // ================= UPDATE SERVICE STATUS =================
    public ServiceEntity updateServiceStatus(Long serviceId, String status) {

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setStatus(status.toUpperCase());

        return serviceRepository.save(service);
    }

    // ================= DELETE SERVICE =================
    public void deleteService(Long serviceId) {

        if (!serviceRepository.existsById(serviceId)) {
            throw new RuntimeException("Service not found with ID: " + serviceId);
        }

        serviceRepository.deleteById(serviceId);
    }
}
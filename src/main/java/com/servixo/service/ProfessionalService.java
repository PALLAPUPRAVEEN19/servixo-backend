package com.servixo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servixo.entity.Professional;
import com.servixo.entity.ServiceEntity;   // ✅ updated entity
import com.servixo.entity.User;
import com.servixo.repository.ProfessionalRepository;
import com.servixo.repository.ServiceRepository;
import com.servixo.repository.UserRepository;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;
    // ✅ GET PROFESSIONAL BY ID
    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    // ✅ ADD SERVICE
    public ServiceEntity createService(Long professionalId, ServiceEntity service) {

        User user = userRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        service.setProfessional(user);   // ✅ CORRECT
        service.setStatus("PENDING");    // ✅ MUST

        return serviceRepository.save(service);
    }
    // ✅ GET SERVICES BY PROFESSIONAL
    public List<ServiceEntity> getServicesByProfessional(Long professionalId) {

        Professional professional = professionalRepository
                .findById(professionalId)
                .orElse(null);

        if (professional == null) {
            throw new RuntimeException("Professional not found with ID: " + professionalId);
        }

        return serviceRepository.findByProfessional_Id(professionalId);
    }

    // ✅ SEARCH SERVICES
    public List<ServiceEntity> searchServices(String keyword) {
    	return serviceRepository
    		    .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    // ✅ DELETE SERVICE
    public void deleteService(Long serviceId) {

        if (!serviceRepository.existsById(serviceId)) {
            throw new RuntimeException("Service not found with ID: " + serviceId);
        }

        serviceRepository.deleteById(serviceId);
    }
}
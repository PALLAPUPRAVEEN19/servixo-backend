package com.servixo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servixo.entity.ServiceEntity;
import com.servixo.service.ServiceService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    // 🔹 CREATE SERVICE (Professional)
    @PostMapping("/{professionalId}")
    public ServiceEntity createService(
            @PathVariable Long professionalId,
            @RequestBody ServiceEntity service) {

        return serviceService.createService(professionalId, service);
    }

    // 🔹 USER - GET APPROVED SERVICES
    @GetMapping
    public List<ServiceEntity> getServices() {
        return serviceService.getApprovedServices();
    }

    // 🔹 ADMIN - GET ALL SERVICES
    @GetMapping("/all")
    public List<ServiceEntity> getAllServices() {
        return serviceService.getAllServices();
    }

    // 🔹 ADMIN - APPROVE SERVICE
    @PutMapping("/approve/{id}")
    public ServiceEntity approveService(@PathVariable Long id) {
        return serviceService.approveService(id);
    }

    // 🔹 ADMIN - REJECT SERVICE
    @PutMapping("/reject/{id}")
    public ServiceEntity rejectService(@PathVariable Long id) {
        return serviceService.rejectService(id);
    }

    // 🔹 PROFESSIONAL - GET OWN SERVICES
    @GetMapping("/professional/{id}")
    public List<ServiceEntity> getMyServices(@PathVariable Long id) {
        return serviceService.getServicesByProfessional(id);
    }

    // 🔹 SEARCH
    @GetMapping("/search")
    public List<ServiceEntity> searchServices(@RequestParam String keyword) {
        return serviceService.searchServices(keyword);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
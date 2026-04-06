package com.servixo.controller;

import com.servixo.entity.Professional;
import com.servixo.entity.ServiceEntity;
import com.servixo.entity.Booking;
import com.servixo.service.ProfessionalService;
import com.servixo.service.ServiceService;
import com.servixo.service.BookingService;
import com.servixo.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional")
@CrossOrigin("*")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    // ================= PROFILE =================
    @GetMapping("/{id}")
    public Professional getProfessional(@PathVariable Long id) {
        return professionalService.getProfessionalById(id);
    }

    // ================= ADD SERVICE =================
    @PostMapping("/services/{professionalId}")
    public ServiceEntity addService(
            @PathVariable Long professionalId,
            @RequestBody ServiceEntity service) {

        return serviceService.createService(professionalId, service); // ✅ FIXED
    }

    // ================= VIEW OWN SERVICES =================
    @GetMapping("/services/{professionalId}")
    public List<ServiceEntity> getMyServices(@PathVariable Long professionalId) {
        return professionalService.getServicesByProfessional(professionalId);
    }

    // ================= VIEW BOOKINGS =================
    @GetMapping("/bookings/{professionalId}")
    public List<Booking> getBookings(@PathVariable Long professionalId) {
        return bookingService.getProfessionalBookings(professionalId);
    }

    // ================= VIEW EARNINGS =================
    @GetMapping("/earnings")
    public double getEarnings() {
        return paymentService.getTotalRevenue(); // later filter by professional
    }
}
package com.servixo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.servixo.entity.ServiceEntity;
import com.servixo.entity.Ticket;
import com.servixo.entity.User;
import com.servixo.service.AdminService;
import com.servixo.service.PaymentService;
import com.servixo.service.ServiceService;
import com.servixo.service.TicketService;
import com.servixo.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AdminService adminService;

    // ================= USERS =================
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ================= SERVICES =================
    @GetMapping("/services")
    public List<ServiceEntity> getAllServices() {
        return serviceService.getAllServices();
    }

    // ✅ APPROVE SERVICE (FIXED - ONLY ONE METHOD)
    @PutMapping("/services/{id}/approve")
    public ServiceEntity approveService(@PathVariable Long id) {
        return serviceService.approveService(id);
    }

    // ================= REVENUE =================
    @GetMapping("/revenue")
    public double getTotalRevenue() {
        return paymentService.getTotalRevenue();
    }

    // ================= TICKETS =================
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // UPDATE TICKET STATUS
    @PutMapping("/tickets/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestParam String status) {
        return ticketService.updateStatus(id, status);
    }

    // ================= STATS =================
    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(adminService.getStats());
    }
    
    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {

        return userService.updateUser(id, updatedUser);
    }
}
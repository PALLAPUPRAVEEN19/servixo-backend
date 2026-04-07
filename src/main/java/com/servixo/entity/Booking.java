package com.servixo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 User who booked
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 👨‍🔧 Professional
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional;

    // 🛠 Service
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    // 📌 Booking Status
    private String status; // PENDING, ACCEPTED, COMPLETED

    // 📅 Service Date
    private LocalDate serviceDate;

    // ⏰ Arrival Time (FIXED)
    private LocalTime arrivalTime;

    // 📍 Address
    private String address;

    // 📝 Instructions
    private String instructions;

    // 🔥 Constructor
    public Booking() {}

    public Booking(User user, User professional, ServiceEntity service, String status,
                   LocalDate serviceDate, LocalTime arrivalTime,
                   String address, String instructions) {
        this.user = user;
        this.professional = professional;
        this.service = service;
        this.status = status;
        this.serviceDate = serviceDate;
        this.arrivalTime = arrivalTime;
        this.address = address;
        this.instructions = instructions;
    }

    // 🔽 GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public User getProfessional() { return professional; }
    public void setProfessional(User professional) { this.professional = professional; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }

    public LocalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
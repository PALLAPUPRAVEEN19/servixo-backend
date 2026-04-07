package com.servixo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    // ✅ FINAL STATUS VALUES
    // PENDING, APPROVED, REJECTED, COMPLETED
    private String status;

    private LocalDate serviceDate;
    private LocalTime arrivalTime;
    private String address;
    private String instructions;

    public Booking() {}

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public User getUser() { return user; }
    public User getProfessional() { return professional; }
    public ServiceEntity getService() { return service; }
    public String getStatus() { return status; }
    public LocalDate getServiceDate() { return serviceDate; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public String getAddress() { return address; }
    public String getInstructions() { return instructions; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setProfessional(User professional) { this.professional = professional; }
    public void setService(ServiceEntity service) { this.service = service; }
    public void setStatus(String status) { this.status = status; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public void setAddress(String address) { this.address = address; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
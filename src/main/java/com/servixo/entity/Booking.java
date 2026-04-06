package com.servixo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Professional professional;

    @ManyToOne
    private ServiceEntity service;

    private String status; // PENDING, COMPLETED

    private LocalDateTime bookingTime;

    // 🔥 NEW FIELDS
    private LocalDate serviceDate;
    private LocalTime arrivalTime;
    private String address;
    private String instructions;

    // Constructors
    public Booking() {}

    public Booking(User user, Professional professional, ServiceEntity service, String status, LocalDateTime bookingTime) {
        this.user = user;
        this.professional = professional;
        this.service = service;
        this.status = status;
        this.bookingTime = bookingTime;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Professional getProfessional() { return professional; }
    public void setProfessional(Professional professional) { this.professional = professional; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    // 🔥 NEW GETTERS & SETTERS

    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }

    public LocalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
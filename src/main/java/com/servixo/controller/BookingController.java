package com.servixo.controller;

import com.servixo.entity.Booking;
import com.servixo.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // ================= CREATE BOOKING =================
    @PostMapping
    public Booking createBooking(
            @RequestParam Long userId,
            @RequestParam Long serviceId) {

        return bookingService.createBooking(userId, serviceId);
    }

    // ================= USER BOOKINGS =================
    @GetMapping("/user/{id}")
    public List<Booking> getUserBookings(@PathVariable Long id) {
        return bookingService.getUserBookings(id);
    }

    // ================= PROFESSIONAL BOOKINGS =================
    @GetMapping("/professional/{id}")
    public List<Booking> getProfessionalBookings(@PathVariable Long id) {
        return bookingService.getProfessionalBookings(id);
    }

    // ================= UPDATE STATUS =================
    @PutMapping("/{id}/status")
    public Booking updateBookingStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return bookingService.updateStatus(id, status);
    }
}
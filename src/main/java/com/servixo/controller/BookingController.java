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
    @PostMapping
    public Booking createBooking(
            @RequestParam Long userId,
            @RequestParam Long serviceId,
            @RequestBody Booking booking) {

        return bookingService.createBooking(userId, serviceId, booking);
    }
    @GetMapping("/user/{id}")
    public List<Booking> getUserBookings(@PathVariable Long id) {
        return bookingService.getUserBookings(id);
    }
}
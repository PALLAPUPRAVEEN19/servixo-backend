package com.servixo.service;

import com.servixo.entity.Booking;
import com.servixo.entity.User;
import com.servixo.entity.ServiceEntity;
import com.servixo.repository.BookingRepository;
import com.servixo.repository.UserRepository;
import com.servixo.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    // ================= CREATE BOOKING =================
    public Booking createBooking(Long userId, Long serviceId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (!"APPROVED".equalsIgnoreCase(service.getStatus())) {
            throw new RuntimeException("Service not approved by admin");
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setService(service);

        // 🔥 MOST IMPORTANT LINE
        booking.setProfessional(service.getProfessional());

        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // ================= UPDATE STATUS =================
    public Booking updateStatus(Long bookingId, String status) {

        status = status.trim().toUpperCase();

        if (!status.equals("PENDING") &&
            !status.equals("ACCEPTED") &&
            !status.equals("REJECTED") &&
            !status.equals("COMPLETED")) {

            throw new RuntimeException("Invalid status value: " + status);
        }

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    // ================= USER BOOKINGS =================
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    // ================= PROFESSIONAL BOOKINGS =================
    public List<Booking> getProfessionalBookings(Long professionalId) {
        return bookingRepository.findByProfessional_Id(professionalId);
    }

    // ================= ALL BOOKINGS =================
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
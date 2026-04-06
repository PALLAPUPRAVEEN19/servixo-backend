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

    // 🔥 CREATE BOOKING (IMPROVED)
    public Booking createBooking(Long userId, Long serviceId, Booking bookingData) {

        // ✅ 1. Validate User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ 2. Validate Service
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // ❌ Prevent booking if not approved
        if (!service.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Service is not approved yet");
        }

        // ❌ Prevent duplicate booking
        boolean exists = bookingRepository.existsByUserAndService(user, service);
        if (exists) {
            throw new RuntimeException("You already booked this service");
        }

        // ✅ 3. Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);

        booking.setServiceDate(bookingData.getServiceDate());
        booking.setArrivalTime(bookingData.getArrivalTime());
        booking.setAddress(bookingData.getAddress());
        booking.setInstructions(bookingData.getInstructions());

        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // 🔹 USER BOOKINGS
    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUser(user);
    }

    // 🔹 PROFESSIONAL BOOKINGS
    public List<Booking> getProfessionalBookings(Long professionalId) {
        return bookingRepository.findByProfessionalId(professionalId);
    }
}
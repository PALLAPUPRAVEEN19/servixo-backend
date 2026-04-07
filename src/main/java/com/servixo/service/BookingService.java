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

    // CREATE BOOKING
    public Booking createBooking(Long userId, Long serviceId, Booking bookingData) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (!"APPROVED".equalsIgnoreCase(service.getStatus())) {
            throw new RuntimeException("Service not approved");
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setService(service);
        booking.setProfessional(service.getProfessional());

        booking.setServiceDate(bookingData.getServiceDate());
        booking.setArrivalTime(bookingData.getArrivalTime());
        booking.setAddress(bookingData.getAddress());
        booking.setInstructions(bookingData.getInstructions());

        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // UPDATE STATUS
    public Booking updateStatus(Long bookingId, String status) {

        status = status.trim().toUpperCase();

        if (!status.equals("PENDING") &&
            !status.equals("APPROVED") &&
            !status.equals("REJECTED") &&
            !status.equals("COMPLETED")) {

            throw new RuntimeException("Invalid status value: " + status);
        }

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    public List<Booking> getProfessionalBookings(Long professionalId) {
        return bookingRepository.findByProfessional_Id(professionalId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
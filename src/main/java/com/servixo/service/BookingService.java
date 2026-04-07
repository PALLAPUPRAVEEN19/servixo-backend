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

    // 🔥 CREATE BOOKING (FINAL VERSION)
    public Booking createBooking(Long userId, Long serviceId, Booking bookingData) {

        System.out.println("🔥 Booking started");

        // ✅ 1. Get User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ 2. Get Service
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // ✅ 3. Check service approval
        if (!"APPROVED".equalsIgnoreCase(service.getStatus())) {
            throw new RuntimeException("Service is not approved yet");
        }

        // ✅ 4. Check professional exists
        if (service.getProfessional() == null) {
            throw new RuntimeException("Service has no professional assigned");
        }

        // ✅ 5. Create Booking
        Booking booking = new Booking();

        booking.setUser(user);
        booking.setService(service);

        // 🔥 IMPORTANT FIX
        booking.setProfessional(service.getProfessional());

        // ✅ Booking details
        booking.setServiceDate(bookingData.getServiceDate());
        booking.setArrivalTime(bookingData.getArrivalTime());

        booking.setAddress(
                bookingData.getAddress() != null ? bookingData.getAddress() : "Not provided"
        );

        booking.setInstructions(
                bookingData.getInstructions() != null ? bookingData.getInstructions() : ""
        );

        // ✅ Default status
        booking.setStatus("PENDING");

        System.out.println("🚀 Saving booking...");

        return bookingRepository.save(booking);
    }

    // 🔥 UPDATE STATUS (ACCEPT / REJECT / COMPLETE)
    public Booking updateStatus(Long bookingId, String status) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ✅ Validate status
        if (!status.equalsIgnoreCase("PENDING") &&
            !status.equalsIgnoreCase("ACCEPTED") &&
            !status.equalsIgnoreCase("REJECTED") &&
            !status.equalsIgnoreCase("COMPLETED")) {

            throw new RuntimeException("Invalid status value");
        }

        booking.setStatus(status.toUpperCase());

        return bookingRepository.save(booking);
    }

    // 🔹 GET USER BOOKINGS
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    // 🔹 GET PROFESSIONAL BOOKINGS
    public List<Booking> getProfessionalBookings(Long professionalId) {
        return bookingRepository.findByProfessional_Id(professionalId);
    }

    // 🔥 OPTIONAL: GET ALL BOOKINGS (ADMIN)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
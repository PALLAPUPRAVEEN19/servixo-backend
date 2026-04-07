package com.servixo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servixo.entity.Booking;
import com.servixo.entity.ServiceEntity;
import com.servixo.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // 🔹 USER BOOKINGS
    List<Booking> findByUser_Id(Long userId);

    // 🔹 PROFESSIONAL BOOKINGS
    List<Booking> findByProfessional_Id(Long professionalId);

    // 🔹 FILTER BY STATUS
    List<Booking> findByStatus(String status);

    // 🔥 USER + STATUS (for history page)
    List<Booking> findByUser_IdAndStatus(Long userId, String status);

    // 🔥 PROFESSIONAL + STATUS (dashboard filtering)
    List<Booking> findByProfessional_IdAndStatus(Long professionalId, String status);

    // 🔥 CHECK DUPLICATE BOOKING (optional future use)
    boolean existsByUserAndService(User user, ServiceEntity service);

    // 🔥 GET COMPLETED BOOKINGS (for earnings)
    
}
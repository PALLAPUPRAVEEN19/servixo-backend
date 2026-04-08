package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.servixo.entity.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    // ================= ACTIVE BOOKINGS =================
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.professional.id = :professionalId AND (b.status = 'PENDING' OR b.status = 'ACCEPTED')")
    long countActiveBookingsForProfessional(@Param("professionalId") Long professionalId);

    // ================= TOTAL BOOKINGS =================
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.professional.id = :professionalId")
    long countTotalBookings(@Param("professionalId") Long professionalId);

    // ================= COMPLETED BOOKINGS =================
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.professional.id = :professionalId AND b.status = 'COMPLETED'")
    long countCompletedBookings(@Param("professionalId") Long professionalId);

    // ================= TOTAL EARNINGS =================
    @Query("SELECT COALESCE(SUM(b.service.price), 0) FROM Booking b WHERE b.professional.id = :professionalId AND b.status = 'COMPLETED'")
    Double getTotalEarnings(@Param("professionalId") Long professionalId);
}
package com.servixo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.servixo.entity.Booking;
import com.servixo.entity.ServiceEntity;
import com.servixo.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ================= USER =================
    List<Booking> findByUser_Id(Long userId);
    long countByUser_Id(Long userId);
    long countByUser_IdAndStatus(Long userId, String status);

    // ================= PROFESSIONAL =================
    List<Booking> findByProfessional_Id(Long professionalId);
    long countByProfessional_Id(Long professionalId);
    long countByProfessional_IdAndStatus(Long professionalId, String status);

    // ================= STATUS =================
    List<Booking> findByStatus(String status);
    long countByStatus(String status);

    // ================= ACTIVE BOOKINGS (USER) =================
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.user.id = :userId AND (b.status = 'PENDING' OR b.status = 'ACCEPTED')")
    long countActiveBookingsForUser(Long userId);

    // ================= ACTIVE BOOKINGS (PROFESSIONAL) =================
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.professional.id = :professionalId AND (b.status = 'PENDING' OR b.status = 'ACCEPTED')")
    long countActiveBookingsForProfessional(Long professionalId);

    // ================= GLOBAL =================
    @Query("SELECT COUNT(b) FROM Booking b")
    long getTotalBookings();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'COMPLETED'")
    long getCompletedBookings();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'PENDING' OR b.status = 'ACCEPTED'")
    long getActiveBookings();

    // ================= EARNINGS =================
    @Query("SELECT COALESCE(SUM(b.service.price), 0) FROM Booking b WHERE b.professional.id = :professionalId AND b.status = 'COMPLETED'")
    Double getTotalEarnings(Long professionalId);

    // ================= PROFESSIONAL BOOKINGS (MAIN) =================
    

    // ================= DUPLICATE CHECK =================
    boolean existsByUserAndService(User user, ServiceEntity service);
}
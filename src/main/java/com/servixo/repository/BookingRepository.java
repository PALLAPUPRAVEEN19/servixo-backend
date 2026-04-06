package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servixo.entity.Booking;
import com.servixo.entity.User;
import com.servixo.entity.Professional;
import com.servixo.entity.ServiceEntity;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByProfessional(Professional professional);

    List<Booking> findByProfessionalId(Long professionalId); // 🔥 NEW

    List<Booking> findByStatus(String status);

    boolean existsByUserAndService(User user, ServiceEntity service); // 🔥 NEW
}
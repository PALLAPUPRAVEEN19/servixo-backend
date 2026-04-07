package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.servixo.entity.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
	@Query("SELECT COUNT(b) FROM Booking b WHERE b.professional.id = :professionalId AND (b.status = 'PENDING' OR b.status = 'APPROVED')")
	long countActiveBookingsForProfessional(Long professionalId);

}
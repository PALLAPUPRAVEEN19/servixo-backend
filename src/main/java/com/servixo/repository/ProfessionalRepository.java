package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servixo.entity.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

}
package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servixo.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStatus(String status);
}
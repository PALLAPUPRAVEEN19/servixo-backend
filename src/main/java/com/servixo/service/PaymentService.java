package com.servixo.service;

import com.servixo.entity.Payment;
import com.servixo.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public double getTotalRevenue() {
        return paymentRepository.findAll()
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}
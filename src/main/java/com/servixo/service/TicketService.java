package com.servixo.service;

import com.servixo.entity.Ticket;
import com.servixo.entity.User;
import com.servixo.repository.TicketRepository;
import com.servixo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔥 CREATE TICKET
    public Ticket createTicket(Long userId, Ticket ticketData) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTitle(ticketData.getTitle());
        ticket.setDescription(ticketData.getDescription());
        ticket.setCategory(ticketData.getCategory());

        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setStatus("OPEN");

        return ticketRepository.save(ticket);
    }

    // 🔹 USER TICKETS
    public List<Ticket> getUserTickets(Long userId) {
        return ticketRepository.findByUser_Id(userId); // ✅ SIMPLIFIED
    }

    // 🔹 ADMIN - ALL
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // 🔥 UPDATE STATUS
    public Ticket updateStatus(Long id, String status) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        status = status.toUpperCase();

        if (!List.of("OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED").contains(status)) {
            throw new RuntimeException("Invalid status");
        }

        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
}
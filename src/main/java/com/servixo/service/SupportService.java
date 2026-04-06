package com.servixo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servixo.entity.Ticket;
import com.servixo.repository.TicketRepository;

@Service
public class SupportService {

    @Autowired
    private TicketRepository ticketRepository;

    // ✅ GET ALL TICKETS
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // ✅ GET TICKETS BY STATUS
    public List<Ticket> getTicketsByStatus(String status) {
        return ticketRepository.findByStatus(status);
    }

    // ✅ UPDATE TICKET STATUS
    public Ticket updateTicketStatus(Long id, String status) {

        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if (ticket == null) {
            throw new RuntimeException("Ticket not found with ID: " + id);
        }

        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }

    // ✅ DELETE TICKET
    public void deleteTicket(Long id) {

        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found with ID: " + id);
        }

        ticketRepository.deleteById(id);
    }
}
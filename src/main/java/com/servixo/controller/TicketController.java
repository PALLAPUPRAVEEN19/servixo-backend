package com.servixo.controller;

import com.servixo.entity.Ticket;
import com.servixo.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // 🔥 CREATE TICKET
    @PostMapping("/{userId}")
    public Ticket createTicket(
            @PathVariable Long userId,
            @RequestBody Ticket ticket) {

        return ticketService.createTicket(userId, ticket);
    }

    // 🔹 USER TICKETS
    @GetMapping("/user/{id}")
    public List<Ticket> getUserTickets(@PathVariable Long id) {
        return ticketService.getUserTickets(id);
    }

    // 🔹 ADMIN VIEW
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // 🔹 UPDATE STATUS (SUPPORT)
    @PutMapping("/{id}")
    public Ticket updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ticketService.updateStatus(id, status);
    }
}
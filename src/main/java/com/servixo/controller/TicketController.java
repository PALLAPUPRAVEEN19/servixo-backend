package com.servixo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servixo.entity.Ticket;
import com.servixo.service.TicketService;

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
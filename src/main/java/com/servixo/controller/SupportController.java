package com.servixo.controller;

import com.servixo.entity.Ticket;
import com.servixo.service.SupportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@CrossOrigin("*")
public class SupportController {

    @Autowired
    private SupportService supportService;

    // ✅ GET ALL TICKETS
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return supportService.getAllTickets();
    }

    // ✅ GET TICKETS BY STATUS
    @GetMapping("/tickets/status")
    public List<Ticket> getTicketsByStatus(@RequestParam String status) {
        return supportService.getTicketsByStatus(status);
    }

    // ✅ UPDATE TICKET STATUS
    @PutMapping("/tickets/{id}")
    public Ticket updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        return supportService.updateTicketStatus(id, status);
    }

    // ✅ DELETE TICKET
    @DeleteMapping("/tickets/{id}")
    public String deleteTicket(@PathVariable Long id) {
        supportService.deleteTicket(id);
        return "Ticket deleted successfully";
    }
}
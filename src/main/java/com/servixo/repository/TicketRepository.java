package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servixo.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	   List<Ticket> findByUser_Id(Long userId); // ✅ correct

    List<Ticket> findByStatus(String status);
}
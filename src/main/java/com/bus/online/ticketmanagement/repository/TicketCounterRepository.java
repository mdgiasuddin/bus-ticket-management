package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCounterRepository extends JpaRepository<TicketCounter, Integer> {
}

package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}

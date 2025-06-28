package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
}

package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.BusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTypeRepository extends JpaRepository<BusType, Integer> {
}

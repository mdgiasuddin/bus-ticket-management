package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {

    boolean existsByName(String name);
}

package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.BusStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStaffRepository extends JpaRepository<BusStaff, Integer> {
}

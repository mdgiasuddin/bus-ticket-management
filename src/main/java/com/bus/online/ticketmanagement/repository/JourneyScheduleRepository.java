package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.JourneySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyScheduleRepository extends JpaRepository<JourneySchedule, Long> {
}

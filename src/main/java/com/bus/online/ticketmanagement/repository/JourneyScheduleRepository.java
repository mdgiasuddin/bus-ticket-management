package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.JourneySchedule;
import com.bus.online.ticketmanagement.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JourneyScheduleRepository extends JpaRepository<JourneySchedule, Long> {
    List<JourneySchedule> findByRouteAndJourneyDateAndActiveIsTrueOrderByStartTime(Route route, LocalDate journeyDate);
}

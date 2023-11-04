package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Trip;
import com.bus.online.ticketmanagement.model.entity.Route;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByRouteAndJourneyDateAndActiveIsTrueOrderByStartTime(Route route, LocalDate journeyDate);

    @EntityGraph(attributePaths = {"seats"})
    Optional<Trip> findTripFetchSeatsById(Long id);
}

package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Seat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @EntityGraph(attributePaths = {"trip.route.endStation"})
    List<Seat> findByIdInOrderByOrdering(Collection<Long> ids);
}

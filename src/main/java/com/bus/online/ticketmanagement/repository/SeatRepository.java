package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.TicketSeat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SeatRepository extends JpaRepository<TicketSeat, Long> {

    @EntityGraph(attributePaths = {"trip.route.endStation"})
    List<TicketSeat> findByIdInOrderByOrdering(Collection<Long> ids);
}

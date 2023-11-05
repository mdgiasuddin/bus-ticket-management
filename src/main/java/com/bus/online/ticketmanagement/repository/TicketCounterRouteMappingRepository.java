package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.TicketCounterRouteId;
import com.bus.online.ticketmanagement.model.entity.TicketCounterRouteMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketCounterRouteMappingRepository extends
        JpaRepository<TicketCounterRouteMapping, TicketCounterRouteId> {

    @Query("select m from TicketCounterRouteMapping m join fetch m.ticketCounter tc join User u " +
            "on u.ticketCounter = tc where m.route = :route and u.id = :userId")
    Optional<TicketCounterRouteMapping> findTicketCounterRouteMapping(Integer userId, Route route);
}

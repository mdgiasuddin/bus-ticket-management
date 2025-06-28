package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketCounterRepository extends JpaRepository<TicketCounter, Integer> {

    @EntityGraph(attributePaths = {"routeMappings.route"})
    Optional<TicketCounter> findCounterFetchRouteMappingById(Integer id);

//    @EntityGraph(attributePaths = {"routeMappings.route.startStation", "routeMappings.route.endStation"})
//    @Query("select tc from User u join u.ticketCounter tc where u.id = :userId")
//    Optional<TicketCounter> findTicketCounterFromUser(Integer userId);
}

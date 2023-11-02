package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Route;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @EntityGraph(attributePaths = {"startStation", "endStation"})
    List<Route> findRouteFetchStationsByIdIsNotNull();
}

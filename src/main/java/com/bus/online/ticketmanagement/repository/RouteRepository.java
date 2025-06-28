package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.Route;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @NonNull
    @EntityGraph(attributePaths = {"startStation", "endStation"})
    List<Route> findAll();

    List<Route> findByIdIn(Collection<Integer> ids);
}

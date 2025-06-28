package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ActionNotPermittedException;
import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.model.dto.request.RouteCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.RouteUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.Station;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.StationRepository;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import com.bus.online.ticketmanagement.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bus.online.ticketmanagement.constant.ExceptionCode.LOGGED_IN_INFORMATION_MISSING;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.ROUTE_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.SAME_START_AND_END_STATION;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.STATION_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final TicketCounterRepository ticketCounterRepository;

    @Transactional
    public void createNewRoute(RouteCreateRequest request) {
        if (request.startStationId().equals(request.endStationId())) {
            throw new RuleViolationException(SAME_START_AND_END_STATION, "Start and end station cannot be the same.");
        }

        Station startStation = stationRepository.findById(request.startStationId())
                .orElseThrow(() -> {
                    log.error("No station found by id: {}", request.startStationId());
                    return new ResourceNotFoundException(STATION_NOT_FOUND, "No station found by id: " + request.startStationId());
                });

        Station endStation = stationRepository.findById(request.endStationId())
                .orElseThrow(() -> {
                    log.error("No station found by id: {}", request.endStationId());
                    return new ResourceNotFoundException(STATION_NOT_FOUND, "No station found by id: " + request.endStationId());
                });

        Route route = new Route();
        route.setStartStation(startStation);
        route.setEndStation(endStation);
        route.setDetails(request.details());
        route.setDistance(request.distance());

        Route reverseRoute = new Route();
        reverseRoute.setStartStation(endStation);
        reverseRoute.setEndStation(startStation);
        reverseRoute.setDetails(request.reverseDetails());
        reverseRoute.setDistance(request.distance());

        routeRepository.save(route);
        routeRepository.save(reverseRoute);
    }

    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(RouteResponse::new)
                .toList();
    }

    public void updateRoute(RouteUpdateRequest request) {
        Route route = routeRepository.findById(request.id())
                .orElseThrow(() -> {
                    log.error("No route found by id: {}", request.id());
                    return new ResourceNotFoundException(ROUTE_NOT_FOUND, "No route found by id: " + request.id());
                });

        route.setDetails(request.details());
        route.setDistance(request.distance());

        routeRepository.save(route);
    }

    public List<RouteResponse> getAllRoutesForCounterMaster() {
        User currentUser = SecurityUtil.getLoggedInUser()
                .orElseThrow(() -> new ActionNotPermittedException(LOGGED_IN_INFORMATION_MISSING, "User not logged in."));

        TicketCounter ticketCounter = null;

        List<Route> routes = new ArrayList<>();
        ticketCounter.getRouteMappings().forEach(mapping -> routes.add(mapping.getRoute()));

        return routes.stream()
                .map(RouteResponse::new)
                .toList();
    }

}

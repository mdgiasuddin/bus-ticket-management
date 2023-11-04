package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ActionNotPermittedException;
import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.mapper.RouteMapper;
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
import com.bus.online.ticketmanagement.utilt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.LOGGED_IN_INFORMATION_MISSING;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.ROUTE_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.SAME_START_AND_END_STATION;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.STATION_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.TICKET_COUNTER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final StationRepository stationRepository;
    private final TicketCounterRepository ticketCounterRepository;

    public void createNewRoute(RouteCreateRequest request) {
        if (request.startStationId().equals(request.endStationId())) {
            throw new RuleViolationException(SAME_START_AND_END_STATION);
        }

        Station startStation = stationRepository.findById(request.startStationId())
                .orElseThrow(() -> new ResourceNotFoundException(STATION_NOT_FOUND));

        Station endStation = stationRepository.findById(request.endStationId())
                .orElseThrow(() -> new ResourceNotFoundException(STATION_NOT_FOUND));

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

        routeRepository.saveAll(Arrays.asList(route, reverseRoute));
    }

    public List<RouteResponse> getAllRoutes() {
        List<Route> routes = routeRepository.findRouteFetchStationsByIdIsNotNull();
        return routeMapper.getResponseFromRoutes(routes);
    }

    public void updateRoute(RouteUpdateRequest request) {
        Route route = routeRepository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(ROUTE_NOT_FOUND));

        route.setDetails(request.details());
        route.setDistance(request.distance());

        routeRepository.save(route);
    }

    public List<RouteResponse> getAllRoutesForCounterMaster() {
        User currentUser = SecurityUtil.getLoggedInUser()
                .orElseThrow(() -> new ActionNotPermittedException(LOGGED_IN_INFORMATION_MISSING));

        TicketCounter ticketCounter = ticketCounterRepository.findTicketCounterFromUser(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException(TICKET_COUNTER_NOT_FOUND));

        List<Route> routes = new ArrayList<>();
        ticketCounter.getRouteMappings().forEach(mapping -> routes.add(mapping.getRoute()));

        return routeMapper.getResponseFromRoutes(routes);
    }
}

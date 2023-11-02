package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.mapper.RouteMapper;
import com.bus.online.ticketmanagement.model.dto.request.RouteCreateRequest;
import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.Station;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.SAME_START_AND_END_STATION;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.STATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final StationRepository stationRepository;

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

        routeRepository.save(route);
    }

    public List<RouteResponse> getAllRoutes() {
        List<Route> routes = routeRepository.findRouteFetchStationsByIdIsNotNull();
        return routeMapper.getResponseFromRoutes(routes);
    }
}

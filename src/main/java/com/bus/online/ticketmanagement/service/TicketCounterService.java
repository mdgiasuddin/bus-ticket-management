package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.mapper.TicketCounterMapper;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterRouteMappingRequest;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.model.entity.TicketCounterRouteMapping;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.MISSING_ENTITIES;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.TICKET_COUNTER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TicketCounterService {

    private final TicketCounterRepository ticketCounterRepository;
    private final RouteRepository routeRepository;
    private final TicketCounterMapper ticketCounterMapper;

    public void createNewTicketCounter(TicketCounterCreateRequest request) {
        TicketCounter ticketCounter = ticketCounterMapper.createTicketCounterFromRequest(request);

        ticketCounterRepository.save(ticketCounter);
    }

    public void updateTicketCounterRouteMapping(TicketCounterRouteMappingRequest request) {
        TicketCounter ticketCounter = ticketCounterRepository.findCounterFetchRouteMappingById(request.ticketCounterId())
                .orElseThrow(() -> new ResourceNotFoundException(TICKET_COUNTER_NOT_FOUND));

        Map<Integer, Integer> routeIdTimeMap = new HashMap<>();
        request.routeWithTimes().forEach(
                routeWithTime -> {
                    routeIdTimeMap.put(routeWithTime.routeId(), routeWithTime.timeDiffFromStartStation());
                }
        );

        Set<Route> updatedRoutes = new HashSet<>(routeRepository.findByIdIn(routeIdTimeMap.keySet()));

        if (request.routeWithTimes().size() != updatedRoutes.size()) {
            throw new RuleViolationException(MISSING_ENTITIES);
        }

        // Remove the deleted route from the list.
        ticketCounter.getRouteMappings().removeIf(mapping -> !updatedRoutes.contains(mapping.getRoute()));

        // Modify the existing route.
        ticketCounter.getRouteMappings().forEach(
                mapping -> mapping.setTimeDiffFromStartStation(routeIdTimeMap.get(mapping.getRoute().getId()))
        );

        updatedRoutes.forEach(
                route -> {
                    TicketCounterRouteMapping routeMapping = new TicketCounterRouteMapping();
                    routeMapping.setTicketCounter(ticketCounter);
                    routeMapping.setRoute(route);
                    routeMapping.setTimeDiffFromStartStation(routeIdTimeMap.get(route.getId()));

                    ticketCounter.getRouteMappings().add(routeMapping);
                }
        );

        ticketCounterRepository.save(ticketCounter);

    }
}

package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterRouteMappingRequest;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.model.entity.TicketCounterRouteMapping;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.bus.online.ticketmanagement.constant.ExceptionCode.MISSING_ENTITIES;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.TICKET_COUNTER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketCounterService {

    private final UserService userService;
    private final TicketCounterRepository ticketCounterRepository;
    private final RouteRepository routeRepository;

    @Transactional
    public void createNewTicketCounter(TicketCounterCreateRequest request) {
        TicketCounter ticketCounter = new TicketCounter();
        ticketCounter.setName(request.name());
        User counterMaster = userService.createCounterMaster(request.counterMaster());
        ticketCounter.setCounterMaster(counterMaster);
        ticketCounterRepository.save(ticketCounter);
    }

    public void updateTicketCounterRouteMapping(TicketCounterRouteMappingRequest request) {
        TicketCounter ticketCounter = ticketCounterRepository.findCounterFetchRouteMappingById(request.ticketCounterId())
                .orElseThrow(() -> {
                    log.error("Ticket counter not found by id: {}", request.ticketCounterId());
                    return new ResourceNotFoundException(TICKET_COUNTER_NOT_FOUND, "Ticket counter not found by id: " + request.ticketCounterId());
                });

        Map<Integer, Integer> routeIdTimeMap = new HashMap<>();
        request.routeWithTimes().forEach(
                routeWithTime -> {
                    routeIdTimeMap.put(routeWithTime.routeId(), routeWithTime.timeDiffFromStartStation());
                }
        );

        Set<Route> updatedRoutes = new HashSet<>(routeRepository.findByIdIn(routeIdTimeMap.keySet()));

        if (request.routeWithTimes().size() != updatedRoutes.size()) {
            throw new RuleViolationException(MISSING_ENTITIES, "missing");
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

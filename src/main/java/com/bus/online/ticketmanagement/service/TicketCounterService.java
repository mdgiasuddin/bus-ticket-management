package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.mapper.TicketCounterMapper;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterRequest;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCounterService {

    private final TicketCounterRepository ticketCounterRepository;
    private final RouteRepository routeRepository;
    private final TicketCounterMapper ticketCounterMapper;

    public void createNewTicketCounter(TicketCounterRequest request) {
        TicketCounter ticketCounter = ticketCounterMapper.createTicketCounterFromRequest(request);
        List<Route> routes = routeRepository.findByIdIn(request.routeIds());
        ticketCounter.setRoutes(new HashSet<>(routes));

        ticketCounterRepository.save(ticketCounter);
    }
}

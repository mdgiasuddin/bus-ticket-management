package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.RouteCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.RouteUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.ROUTE_ENDPOINT;

@RestController
@RequestMapping(ROUTE_ENDPOINT)
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void createNewRoute(@RequestBody @Valid RouteCreateRequest request) {
        routeService.createNewRoute(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public void updateRoute(@RequestBody @Valid RouteUpdateRequest request) {
        routeService.updateRoute(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<RouteResponse> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/counterMaster")
    public List<RouteResponse> getAllRoutesForCounterMaster() {
        return routeService.getAllRoutesForCounterMaster();
    }
}

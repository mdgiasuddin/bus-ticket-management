package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.RouteCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.RouteUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create a new bus route.")
    public void createNewRoute(@RequestBody @Valid RouteCreateRequest request) {
        routeService.createNewRoute(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    @Operation(summary = "Update existing bus route.")
    public void updateRoute(@RequestBody @Valid RouteUpdateRequest request) {
        routeService.updateRoute(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all the bus routes.")
    public List<RouteResponse> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/counterMaster")
    @Operation(summary = "Get the bus routes permitted to to a counter-master.")
    public List<RouteResponse> getAllRoutesForCounterMaster() {
        return routeService.getAllRoutesForCounterMaster();
    }
}

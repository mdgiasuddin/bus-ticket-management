package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.RouteCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.RouteUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @GetMapping
    @Operation(summary = "Get all the bus routes.")
    public List<RouteResponse> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PostMapping
    @Operation(summary = "Create a new bus route.")
    public void createNewRoute(@RequestBody @Valid RouteCreateRequest request) {
        routeService.createNewRoute(request);
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PutMapping
    @Operation(summary = "Update existing bus route.")
    public void updateRoute(@RequestBody @Valid RouteUpdateRequest request) {
        routeService.updateRoute(request);
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/v1/counter-master")
    @Operation(summary = "Get the bus routes permitted to to a counter-master.")
    public List<RouteResponse> getAllRoutesForCounterMaster() {
        return routeService.getAllRoutesForCounterMaster();
    }
}

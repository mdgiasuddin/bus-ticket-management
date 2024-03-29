package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.TicketCounterCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.TicketCounterRouteMappingRequest;
import com.bus.online.ticketmanagement.service.TicketCounterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.TICKET_COUNTER_ENDPOINT;

@RestController
@RequestMapping(TICKET_COUNTER_ENDPOINT)
@RequiredArgsConstructor
public class TicketCounterController {

    private final TicketCounterService ticketCounterService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new ticket counter.")
    public void createNewTicketCounter(@RequestBody @Valid TicketCounterCreateRequest request) {
        ticketCounterService.createNewTicketCounter(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/v1/routes")
    @Operation(summary = "Update route permissions of a counter master. Always take the updated list of routes.")
    public void updateTicketCounterRouteMapping(@RequestBody @Valid TicketCounterRouteMappingRequest request) {
        ticketCounterService.updateTicketCounterRouteMapping(request);
    }
}

package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TicketCounterRouteMappingRequest(
        @NotNull
        Integer ticketCounterId,
        @NotEmpty
        List<RouteWithTime> routeWithTimes
) {
}

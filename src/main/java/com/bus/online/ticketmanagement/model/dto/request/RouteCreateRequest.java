package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteCreateRequest(
        @NotNull
        Integer startStationId,
        @NotNull
        Integer endStationId,
        @NotBlank
        String details,
        @NotNull
        Integer distance
) {
}

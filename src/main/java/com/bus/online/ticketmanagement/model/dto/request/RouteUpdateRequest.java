package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteUpdateRequest(
        @NotNull
        Integer id,
        @NotBlank
        String details,
        @NotNull
        Integer distance
) {
}

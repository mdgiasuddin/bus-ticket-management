package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusTypeCreateRequest(
        @NotBlank
        String name,

        @NotNull
        Integer numberOfSeats,

        @NotNull
        Integer numberOfRows
) {
}

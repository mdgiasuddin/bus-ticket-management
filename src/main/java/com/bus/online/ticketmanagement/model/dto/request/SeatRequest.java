package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SeatRequest(
        @NotNull
        Long id,
        @NotNull
        UUID idKey
) {
}

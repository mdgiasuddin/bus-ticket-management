package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StationRequest(
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String district
) {
}

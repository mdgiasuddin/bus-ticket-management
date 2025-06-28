package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StationUpdateRequest(
        @NotNull
        Integer id,

        @NotBlank
        String name,

        @NotBlank
        String district
) {
}

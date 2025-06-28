package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusCreateRequest(
        @NotBlank
        String registrationNumber,

        @NotNull
        Integer busTypeId
) {
}

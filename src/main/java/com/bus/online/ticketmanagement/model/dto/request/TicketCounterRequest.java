package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record TicketCounterRequest(
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String masterName,
        @NotBlank
        String address,
        @NotBlank
        String mobileNumber,
        String optionalMobileNumber,
        @NotNull
        Set<Integer> routeIds
) {
}

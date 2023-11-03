package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TicketCounterCreateRequest(
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String masterName,
        @NotBlank
        String address,
        @NotBlank
        String mobileNumber,
        String optionalMobileNumber
) {
}

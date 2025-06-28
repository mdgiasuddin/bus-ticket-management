package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TicketCounterUpdateRequest(
        @NotNull
        Integer id,

        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-z0-9]{8,}$", message = "Must have 8 characters with lowercase letters or digits")
        String username,

        @NotBlank
        String masterName,

        @NotBlank
        String address,

        @NotBlank
        String mobileNumber,

        String optionalMobileNumber
) {
}

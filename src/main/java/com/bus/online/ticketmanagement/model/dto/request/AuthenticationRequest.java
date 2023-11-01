package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}

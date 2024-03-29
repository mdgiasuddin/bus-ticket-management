package com.bus.online.ticketmanagement.model.dto.response;

import java.util.UUID;

public record AuthenticationResponse(
        String accessToken,
        UUID authorizationKey
) {
}

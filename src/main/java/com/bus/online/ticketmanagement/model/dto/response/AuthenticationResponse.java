package com.bus.online.ticketmanagement.model.dto.response;

public record AuthenticationResponse(
        String accessToken,
        String authorizationKey
) {
}

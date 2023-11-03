package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record RouteWithTime(
        @NotNull
        Integer routeId,
        @NotNull
        Integer timeDiffFromStartStation
) {

}

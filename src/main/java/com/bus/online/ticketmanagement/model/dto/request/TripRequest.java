package com.bus.online.ticketmanagement.model.dto.request;

import com.bus.online.ticketmanagement.model.enums.BusType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TripRequest(
        @NotNull
        LocalDate journeyDate,
        @NotNull
        String startTime,
        @NotNull
        Short coachNumber,
        @NotNull
        Integer routeId,
        @NotNull
        Double fare,
        @NotNull
        Double commission,
        @NotNull
        BusType busType
) {
}

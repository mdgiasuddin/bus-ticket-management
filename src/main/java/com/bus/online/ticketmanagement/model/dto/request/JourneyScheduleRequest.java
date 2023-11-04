package com.bus.online.ticketmanagement.model.dto.request;

import com.bus.online.ticketmanagement.model.enums.BusType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record JourneyScheduleRequest(
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
        BusType busType
) {
}

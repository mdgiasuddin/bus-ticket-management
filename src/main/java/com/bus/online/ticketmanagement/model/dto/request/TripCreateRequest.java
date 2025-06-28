package com.bus.online.ticketmanagement.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record TripCreateRequest(
        @NotNull
        LocalDate journeyDate,

        @NotNull
        @JsonFormat(pattern = "hh:mm")
        LocalTime startTime,

        @NotNull
        Short coachNumber,

        @NotNull
        Integer routeId,

        @NotNull
        Double fare,

        @NotNull
        Integer busTypeId,

        @NotNull
        Integer busId,

        @NotNull
        Integer supervisorId,

        @NotNull
        Integer driverId,

        @NotNull
        Integer helperId

) {
}

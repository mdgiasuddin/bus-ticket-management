package com.bus.online.ticketmanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripResponse {
    private Long id;
    private LocalDate journeyDate;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;
    private UUID idKey;
    private Double fare;
    private BusTypeResponse busType;
}

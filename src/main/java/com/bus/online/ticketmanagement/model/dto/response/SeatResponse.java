package com.bus.online.ticketmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatResponse {
    private Long id;
    private String seatNumber;
    private UUID idKey;
    private Short ordering;
    private Boolean booked;
}

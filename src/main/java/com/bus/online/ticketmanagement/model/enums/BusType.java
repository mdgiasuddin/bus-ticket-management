package com.bus.online.ticketmanagement.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BusType {
    N40(40, 10, "Non AC 40 Seats"),
    N36(36, 9, "Non AC 36 Seats"),
    A40(40, 10, "AC 40 Seats"),
    A36(36, 9, "AC 36 Seats"),
    A27(27, 9, "AC 27 Seats");

    private final int numberOfSeats;
    private final int numberOfRows;
    private final String description;
}

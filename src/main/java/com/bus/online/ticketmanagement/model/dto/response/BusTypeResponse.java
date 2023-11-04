package com.bus.online.ticketmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusTypeResponse {
    private String name;
    private int numberOfSeats;
    private int numberOfRows;
    private String description;
}

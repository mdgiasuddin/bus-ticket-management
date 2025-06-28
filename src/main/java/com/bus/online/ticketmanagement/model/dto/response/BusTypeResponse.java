package com.bus.online.ticketmanagement.model.dto.response;

import com.bus.online.ticketmanagement.model.entity.BusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusTypeResponse {
    private Integer id;
    private String name;
    private int numberOfSeats;
    private int numberOfRows;

    public BusTypeResponse(BusType busType) {
        this.id = busType.getId();
        this.name = busType.getName();
        this.numberOfSeats = busType.getNumberOfSeats();
        this.numberOfRows = busType.getNumberOfRows();
    }
}

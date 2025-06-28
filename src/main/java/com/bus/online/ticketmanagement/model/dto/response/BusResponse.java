package com.bus.online.ticketmanagement.model.dto.response;

import com.bus.online.ticketmanagement.model.entity.Bus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusResponse {
    private Integer id;
    private String registrationNumber;
    private BusTypeResponse busType;

    public BusResponse(Bus bus) {
        this.id = bus.getId();
        this.registrationNumber = bus.getRegistrationNumber();
        this.busType = new BusTypeResponse(bus.getBusType());
    }
}

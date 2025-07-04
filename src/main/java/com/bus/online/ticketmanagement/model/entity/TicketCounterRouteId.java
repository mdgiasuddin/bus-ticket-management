package com.bus.online.ticketmanagement.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TicketCounterRouteId implements Serializable {

    private TicketCounter ticketCounter;
    private Route route;
}

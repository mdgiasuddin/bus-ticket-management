package com.bus.online.ticketmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketCounterRouteId implements Serializable {

    private TicketCounter ticketCounter;
    private Route route;
}

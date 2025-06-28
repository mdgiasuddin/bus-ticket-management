package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(TicketCounterRouteId.class)
public class TicketCounterRouteMapping {

    @Id
    @ManyToOne(fetch = LAZY)
    private TicketCounter ticketCounter;

    @Id
    @ManyToOne(fetch = LAZY)
    private Route route;

    private Integer timeDiffFromStartStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCounterRouteMapping that = (TicketCounterRouteMapping) o;
        return Objects.equals(ticketCounter, that.ticketCounter) && Objects.equals(route, that.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCounter, route);
    }
}

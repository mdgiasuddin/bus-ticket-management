package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Getter
@Setter
public class BusType {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;
    private int numberOfSeats;
    private int numberOfRows;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BusType busType)) return false;
        return Objects.equals(id, busType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

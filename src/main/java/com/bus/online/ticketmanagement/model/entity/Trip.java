package com.bus.online.ticketmanagement.model.entity;

import com.bus.online.ticketmanagement.util.RandomStringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate journeyDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false, columnDefinition = "smallint")
    private Short coachNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private Route route;

    @Column(nullable = false, columnDefinition = "varchar(10)")
    private String idKey;

    @Column(nullable = false, columnDefinition = "decimal(6,0)")
    private Double fare;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private Bus bus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private BusType busType;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusStaff supervisor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusStaff driver;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusStaff helper;

    @OneToMany(
            mappedBy = "trip", fetch = LAZY,
            cascade = ALL, orphanRemoval = true
    )
    private Set<TicketSeat> seats = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    public void prePersist() {
        idKey = RandomStringUtil.randomString(10);
    }
}

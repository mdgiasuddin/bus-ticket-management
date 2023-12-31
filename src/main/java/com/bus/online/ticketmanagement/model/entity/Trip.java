package com.bus.online.ticketmanagement.model.entity;

import com.bus.online.ticketmanagement.model.enums.BusType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate journeyDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false, columnDefinition = "smallint")
    private Short coachNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Route route;

    @Column(nullable = false)
    private UUID idKey = UUID.randomUUID();

    @Column(nullable = false, columnDefinition = "decimal(6,0)")
    private Double fare;

    @Column(nullable = false, columnDefinition = "decimal(4,0)")
    private Double commission;

    @Column(nullable = false, columnDefinition = "varchar(3)")
    @Enumerated(EnumType.STRING)
    private BusType busType;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    @OneToMany(
            mappedBy = "trip", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true
    )
    private Set<Seat> seats = new HashSet<>();

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
}

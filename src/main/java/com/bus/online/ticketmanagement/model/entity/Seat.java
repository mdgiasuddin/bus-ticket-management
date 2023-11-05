package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(2)")
    private String seatNumber;

    @Column(nullable = false)
    private UUID idKey = UUID.randomUUID();

    @Column(nullable = false, columnDefinition = "smallint")
    private Short ordering;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean booked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookingInfo bookingInfo;
}

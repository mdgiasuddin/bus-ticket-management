package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(40)")
    private String bookedBy;

    @Column(nullable = false)
    private LocalDateTime bookedAt = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "varchar(40)")
    private String passengerName;

    @Column(nullable = false, columnDefinition = "varchar(15)")
    private String mobileNumber;

    @Column(nullable = false, columnDefinition = "varchar(120)")
    private String seatDetails;

    @Column(nullable = false)
    private LocalDateTime journeyStartTime;

    @Column(nullable = false, columnDefinition = "varchar(40)")
    private String startFrom;

    @Column(nullable = false, columnDefinition = "varchar(25)")
    private String endTo;

    @Column(nullable = false, columnDefinition = "decimal(6,0)")
    private Double fare;

    @ManyToOne(fetch = LAZY)
    private TicketCounter boardingPoint;

    @ManyToOne(fetch = LAZY)
    private Trip trip;

    @OneToMany(
            mappedBy = "bookingInfo", fetch = LAZY,
            cascade = PERSIST
    )
    private Set<TicketSeat> seats = new HashSet<>();
}

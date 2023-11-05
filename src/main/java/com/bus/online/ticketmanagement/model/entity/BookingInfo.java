package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, columnDefinition = "decimal(4,0)")
    private Double commission;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketCounter ticketCounter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;

    @OneToMany(
            mappedBy = "bookingInfo", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private Set<Seat> seats = new HashSet<>();
}

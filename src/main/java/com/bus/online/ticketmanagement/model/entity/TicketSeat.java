package com.bus.online.ticketmanagement.model.entity;

import com.bus.online.ticketmanagement.util.RandomStringUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Getter
@Setter
public class TicketSeat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(2)")
    private String seatNumber;

    @Column(nullable = false, columnDefinition = "varchar(10)")
    private String idKey;

    @Column(nullable = false, columnDefinition = "smallint")
    private Short ordering;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean booked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookingInfo bookingInfo;

    @PrePersist
    public void prePersist() {
        idKey = RandomStringUtil.randomString(10);
    }
}

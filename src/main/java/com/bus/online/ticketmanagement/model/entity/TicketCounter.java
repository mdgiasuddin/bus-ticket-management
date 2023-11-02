package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String masterName;

    @Column(nullable = false, columnDefinition = "varchar(150)")
    private String address;

    @Column(nullable = false, columnDefinition = "varchar(15)")
    private String mobileNumber;

    @Column(columnDefinition = "varchar(15)")
    private String optionalMobileNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ticket_counter_route_mapping",
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private Set<Route> routes;
}

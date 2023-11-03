package com.bus.online.ticketmanagement.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
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

    @OneToMany(
            mappedBy = "ticketCounter", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true
    )
    private Set<TicketCounterRouteMapping> routeMappings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCounter that = (TicketCounter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.bus.online.ticketmanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingInfoResponse {
    private Long id;
    private String bookedBy;
    @JsonFormat(pattern = "dd MMM yyyy h:ma")
    private LocalDateTime bookedAt;
    private String passengerName;
    private String mobileNumber;
    private String seatDetails;
    @JsonFormat(pattern = "dd MMM yyyy h:ma")
    private LocalDateTime journeyStartTime;
    @JsonFormat(pattern = "dd MMM yyyy h:ma")
    private LocalDateTime presenceTime;
    private String startFrom;
    private String endTo;
    private Double fare;
    private Double commission;
}

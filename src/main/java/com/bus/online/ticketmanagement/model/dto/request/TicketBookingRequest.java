package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TicketBookingRequest(
        @NotBlank
        String passengerName,
        @NotBlank
        String mobileNumber,
        @NotEmpty
        List<SeatRequest> seatRequests
) {
}

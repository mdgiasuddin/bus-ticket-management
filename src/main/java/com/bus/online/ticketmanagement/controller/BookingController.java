package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.TicketBookingRequest;
import com.bus.online.ticketmanagement.model.dto.response.BookingInfoResponse;
import com.bus.online.ticketmanagement.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.BOOKING_ENDPOINT;

@RestController
@RequestMapping(BOOKING_ENDPOINT)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @PostMapping
    public BookingInfoResponse bookTicket(@RequestBody @Valid TicketBookingRequest request) {
        return bookingService.bookTicket(request);
    }
}

package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.TripRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.dto.response.TripResponse;
import com.bus.online.ticketmanagement.model.dto.response.SeatResponse;
import com.bus.online.ticketmanagement.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.TRIP_ENDPOINT;

@RestController
@RequestMapping(TRIP_ENDPOINT)
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void createNewTrip(@RequestBody @Valid TripRequest request) {
        tripService.createNewTrip(request);
    }

    @GetMapping("/busTypes")
    public List<BusTypeResponse> getAllBusTypes() {
        return tripService.getAllBusTypes();
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/{routeId}/{journeyDate}")
    public List<TripResponse> getAllTripsOfARoute(
            @PathVariable int routeId,
            @PathVariable LocalDate journeyDate,
            @RequestParam("idKey") UUID idKey
    ) {

        return tripService.getAllTripsOfARoute(routeId, journeyDate, idKey);
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/{tripId}/seats")
    public List<SeatResponse> getAllSeatsForATrip(
            @PathVariable long tripId,
            @RequestParam("idKey") UUID idKey
    ) {

        return tripService.getAllSeatsForATrip(tripId, idKey);
    }
}

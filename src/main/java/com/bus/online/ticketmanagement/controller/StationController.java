package com.bus.online.ticketmanagement.controller;


import com.bus.online.ticketmanagement.model.dto.request.StationRequest;
import com.bus.online.ticketmanagement.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.STATION_ENDPOINT;

@RestController
@RequestMapping(STATION_ENDPOINT)
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new bus station.")
    public void createNewStation(@RequestBody @Valid StationRequest request) {
        stationService.createNewStation(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    @Operation(summary = "Update existing bus station.")
    public void updateStation(@RequestBody @Valid StationRequest request) {
        stationService.updateStation(request);
    }
}

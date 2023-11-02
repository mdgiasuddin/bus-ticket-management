package com.bus.online.ticketmanagement.controller;


import com.bus.online.ticketmanagement.model.dto.request.StationRequest;
import com.bus.online.ticketmanagement.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.STATION_ENDPOINT;

@RestController
@RequestMapping(STATION_ENDPOINT)
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void createNewStation(@RequestBody @Valid StationRequest request) {
        stationService.createNewStation(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public void updateStation(@RequestBody @Valid StationRequest request) {
        stationService.updateStation(request);
    }
}

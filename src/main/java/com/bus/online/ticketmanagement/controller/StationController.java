package com.bus.online.ticketmanagement.controller;


import com.bus.online.ticketmanagement.model.dto.request.StationCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.StationUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.StationResponse;
import com.bus.online.ticketmanagement.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @GetMapping
    @Operation(summary = "Get list of all bus stations.")
    public List<StationResponse> getAllStations() {
        return stationService.getAllStations();
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PostMapping
    @Operation(summary = "Create a new bus station.")
    public void createNewStation(@RequestBody @Valid StationCreateRequest request) {
        stationService.createNewStation(request);
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PutMapping
    @Operation(summary = "Update existing bus station.")
    public void updateStation(@RequestBody @Valid StationUpdateRequest request) {
        stationService.updateStation(request);
    }
}

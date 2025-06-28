package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.BusCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.BusTypeCreateRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusResponse;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.service.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @GetMapping
    public List<BusResponse> getAllBuses() {
        return busService.getAllBuses();
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PostMapping
    public void createNewBus(@Valid @RequestBody BusCreateRequest request) {
        busService.createNewBus(request);
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @GetMapping("/bus-types")
    public List<BusTypeResponse> getAllBusTypes() {
        return busService.getAllBusTypes();
    }

    @PreAuthorize("hasAnyRole('DATA_ENTRY')")
    @PostMapping("/bus-types")
    public void createNewBusType(@Valid @RequestBody BusTypeCreateRequest request) {
        busService.createNewBusType(request);
    }
}

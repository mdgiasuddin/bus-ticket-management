package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.JourneyScheduleRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.dto.response.JourneyScheduleResponse;
import com.bus.online.ticketmanagement.service.JourneyScheduleService;
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

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.JOURNEY_SCHEDULE_ENDPOINT;

@RestController
@RequestMapping(JOURNEY_SCHEDULE_ENDPOINT)
@RequiredArgsConstructor
public class JourneyScheduleController {

    private final JourneyScheduleService journeyScheduleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void createNewJourneySchedule(@RequestBody @Valid JourneyScheduleRequest request) {
        journeyScheduleService.createNewJourneySchedule(request);
    }

    @GetMapping("/busTypes")
    public List<BusTypeResponse> getAllBusTypes() {
        return journeyScheduleService.getAllBusTypes();
    }

    @PreAuthorize("hasAnyRole('COUNTER_MASTER')")
    @GetMapping("/{routeId}/{journeyDate}")
    public List<JourneyScheduleResponse> getAllJourneysOfARoute(
            @PathVariable int routeId,
            @PathVariable LocalDate journeyDate,
            @RequestParam("idKey") UUID idKey
    ) {

        return journeyScheduleService.getAllJourneysOfARoute(routeId, journeyDate, idKey);
    }
}

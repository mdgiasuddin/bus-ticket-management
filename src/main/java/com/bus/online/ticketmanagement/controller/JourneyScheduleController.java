package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.JourneyScheduleRequest;
import com.bus.online.ticketmanagement.service.JourneyScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

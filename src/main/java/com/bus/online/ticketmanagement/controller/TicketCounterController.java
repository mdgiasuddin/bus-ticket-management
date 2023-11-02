package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.TicketCounterRequest;
import com.bus.online.ticketmanagement.service.TicketCounterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.TICKET_COUNTER_ENDPOINT;

@RestController
@RequestMapping(TICKET_COUNTER_ENDPOINT)
@RequiredArgsConstructor
public class TicketCounterController {

    private final TicketCounterService ticketCounterService;

    @PostMapping
    public void createNewTicketCounter(@RequestBody @Valid TicketCounterRequest request) {
        ticketCounterService.createNewTicketCounter(request);
    }
}

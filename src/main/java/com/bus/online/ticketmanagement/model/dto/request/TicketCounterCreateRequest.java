package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record TicketCounterCreateRequest(
        @NotBlank
        String name,

        @Valid
        CounterMasterRequest counterMaster
) {
}

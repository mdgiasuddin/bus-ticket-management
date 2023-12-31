package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.request.TicketCounterCreateRequest;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface TicketCounterMapper {

    TicketCounter createTicketCounterFromRequest(TicketCounterCreateRequest request);
}

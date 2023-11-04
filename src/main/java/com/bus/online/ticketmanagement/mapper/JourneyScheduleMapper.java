package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.dto.response.JourneyScheduleResponse;
import com.bus.online.ticketmanagement.model.entity.JourneySchedule;
import com.bus.online.ticketmanagement.model.enums.BusType;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface JourneyScheduleMapper {

    @Mapping(source = "busType", target = "busType", qualifiedByName = "processBusType")
    List<JourneyScheduleResponse> getResponseFromJourneySchedule(List<JourneySchedule> schedules);

    @Named("processBusType")
    default BusTypeResponse processBusType(BusType busType) {
        return new BusTypeResponse(
                busType.name(),
                busType.getNumberOfSeats(),
                busType.getNumberOfRows(),
                busType.getDescription()
        );
    }
}

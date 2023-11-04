package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.response.SeatResponse;
import com.bus.online.ticketmanagement.model.dto.response.TripResponse;
import com.bus.online.ticketmanagement.model.entity.Seat;
import com.bus.online.ticketmanagement.model.entity.Trip;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface TripMapper {

    List<TripResponse> getResponseFromTrip(List<Trip> trips);
    List<SeatResponse> getResponseFromSeats(Collection<Seat> seats);

}

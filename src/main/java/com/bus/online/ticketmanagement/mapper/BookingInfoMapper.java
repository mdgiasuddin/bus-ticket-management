package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.response.BookingInfoResponse;
import com.bus.online.ticketmanagement.model.entity.BookingInfo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface BookingInfoMapper {

    BookingInfoResponse getResponseFromBookingInfo(BookingInfo bookingInfo);
}

package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ActionNotPermittedException;
import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.RuleViolationException;
import com.bus.online.ticketmanagement.mapper.BookingInfoMapper;
import com.bus.online.ticketmanagement.model.dto.request.TicketBookingRequest;
import com.bus.online.ticketmanagement.model.dto.response.BookingInfoResponse;
import com.bus.online.ticketmanagement.model.entity.BookingInfo;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.Seat;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.model.entity.TicketCounterRouteMapping;
import com.bus.online.ticketmanagement.model.entity.Trip;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.BookingInfoRepository;
import com.bus.online.ticketmanagement.repository.SeatRepository;
import com.bus.online.ticketmanagement.repository.TicketCounterRouteMappingRepository;
import com.bus.online.ticketmanagement.utilt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.bus.online.ticketmanagement.constant.ApplicationConstants.PRESENCE_BEFORE_START;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.LOGGED_IN_INFORMATION_MISSING;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.MISSING_ENTITIES;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.ROUTE_MAPPING_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.SEAT_ALREADY_BOOKED;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.SEAT_NOT_PERMITTED;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingInfoRepository bookingInfoRepository;
    private final SeatRepository seatRepository;
    private final TicketCounterRouteMappingRepository ticketCounterRouteMappingRepository;
    private final BookingInfoMapper bookingInfoMapper;

    public BookingInfoResponse bookTicket(TicketBookingRequest request) {
        Map<Long, UUID> idKeyMap = new HashMap<>();
        request.seatRequests().forEach(
                seatRequest -> {
                    idKeyMap.put(seatRequest.id(), seatRequest.idKey());
                }
        );

        List<Seat> seats = seatRepository.findByIdInOrderByOrdering(idKeyMap.keySet());
        if (seats.size() != request.seatRequests().size()) {
            throw new RuleViolationException(MISSING_ENTITIES);
        }

        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new RuleViolationException(SEAT_ALREADY_BOOKED);
            } else if (!idKeyMap.get(seat.getId()).equals(seat.getIdKey())) {
                throw new ActionNotPermittedException(SEAT_NOT_PERMITTED);
            }
        }

        Trip trip = seats.get(0).getTrip();
        Route route = trip.getRoute();
        User currentUser = SecurityUtil.getLoggedInUser()
                .orElseThrow(() -> new ActionNotPermittedException(LOGGED_IN_INFORMATION_MISSING));

        TicketCounterRouteMapping routeMapping = ticketCounterRouteMappingRepository.findTicketCounterRouteMapping(currentUser.getId(), route)
                .orElseThrow(() -> new ResourceNotFoundException(ROUTE_MAPPING_NOT_FOUND));

        TicketCounter ticketCounter = routeMapping.getTicketCounter();

        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setBookedBy(currentUser.getName());
        bookingInfo.setPassengerName(request.passengerName());
        bookingInfo.setMobileNumber(request.mobileNumber());
        bookingInfo.setJourneyStartTime(
                trip.getJourneyDate().atTime(trip.getStartTime()).plusMinutes(routeMapping.getTimeDiffFromStartStation())
        );
        bookingInfo.setStartFrom(ticketCounter.getName());
        bookingInfo.setEndTo(route.getEndStation().getName());
        bookingInfo.setFare(trip.getFare());
        bookingInfo.setCommission(trip.getCommission());
        bookingInfo.setTrip(trip);
        bookingInfo.setSeats(new HashSet<>(seats));

        List<String> seatDetails = new ArrayList<>();
        seats.forEach(seat -> {
            seatDetails.add(seat.getSeatNumber());
            seat.setBookingInfo(bookingInfo);
            seat.setBooked(true);
        });
        bookingInfo.setSeatDetails(String.join(",", seatDetails));
        BookingInfoResponse response = bookingInfoMapper.getResponseFromBookingInfo(
                bookingInfoRepository.save(bookingInfo)
        );
        response.setPresenceTime(bookingInfo.getJourneyStartTime().minusMinutes(PRESENCE_BEFORE_START));

        return response;
    }
}

package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ActionNotPermittedException;
import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.mapper.TripMapper;
import com.bus.online.ticketmanagement.model.dto.request.TripRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.dto.response.TripResponse;
import com.bus.online.ticketmanagement.model.dto.response.SeatResponse;
import com.bus.online.ticketmanagement.model.entity.Trip;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.Seat;
import com.bus.online.ticketmanagement.model.enums.BusType;
import com.bus.online.ticketmanagement.repository.TripRepository;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.*;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final RouteRepository routeRepository;
    private final TripMapper tripMapper;

    public void createNewTrip(TripRequest request) {
        Route route = routeRepository.findById(request.routeId())
                .orElseThrow(() -> new ResourceNotFoundException(ROUTE_NOT_FOUND));

        Trip trip = new Trip();
        trip.setJourneyDate(request.journeyDate());
        trip.setStartTime(LocalTime.parse(request.startTime()));
        trip.setCoachNumber(request.coachNumber());
        trip.setRoute(route);
        trip.setFare(request.fare());
        trip.setBusType(request.busType());

        int rows = request.busType().getNumberOfRows();
        int cols = request.busType().getNumberOfSeats() / rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                Seat seat = new Seat();
                seat.setSeatNumber((char) ('A' + i) + "" + j);
                seat.setOrdering((short) (i * cols + j));
                seat.setTrip(trip);

                trip.getSeats().add(seat);
            }
        }

        tripRepository.save(trip);
    }

    public List<TripResponse> getAllTripsOfARoute(int routeId, LocalDate journeyDate, UUID idKey) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException(ROUTE_NOT_FOUND));

        if (!route.getIdKey().equals(idKey)) {
            throw new ActionNotPermittedException(ROUTE_NOT_PERMITTED);
        }

        if (journeyDate.isBefore(LocalDate.now())) {
            throw new ActionNotPermittedException(INVALID_DATE);
        }

        List<Trip> trips = tripRepository
                .findByRouteAndJourneyDateAndActiveIsTrueOrderByStartTime(route, journeyDate);


        return tripMapper.getResponseFromTrip(trips);
    }

    public List<BusTypeResponse> getAllBusTypes() {
        List<BusTypeResponse> response = new ArrayList<>();
        for (BusType busType : BusType.values()) {
            response.add(
                    new BusTypeResponse(
                            busType.name(),
                            busType.getNumberOfSeats(),
                            busType.getNumberOfRows(),
                            busType.getDescription()
                    )
            );
        }

        return response;
    }

    public List<SeatResponse> getAllSeatsForATrip(long tripId, UUID idKey) {
        Trip trip = tripRepository.findTripFetchSeatsById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException(TRIP_NOT_FOUND));

        if (!trip.getIdKey().equals(idKey)) {
            throw new ActionNotPermittedException(TRIP_NOT_PERMITTED);
        }

        List<SeatResponse> response = tripMapper.getResponseFromSeats(trip.getSeats());
        response.sort(Comparator.comparingInt(SeatResponse::getOrdering));

        return response;
    }
}

package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ActionNotPermittedException;
import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.model.dto.request.TripCreateRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.dto.response.SeatResponse;
import com.bus.online.ticketmanagement.model.dto.response.TripResponse;
import com.bus.online.ticketmanagement.model.entity.BusStaff;
import com.bus.online.ticketmanagement.model.entity.BusType;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.TicketSeat;
import com.bus.online.ticketmanagement.model.entity.Trip;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import com.bus.online.ticketmanagement.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.bus.online.ticketmanagement.constant.ExceptionCode.INVALID_DATE;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.ROUTE_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.ROUTE_NOT_PERMITTED;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.TRIP_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionCode.TRIP_NOT_PERMITTED;
import static com.bus.online.ticketmanagement.model.enumeration.StaffType.DRIVER;
import static com.bus.online.ticketmanagement.model.enumeration.StaffType.HELPER;
import static com.bus.online.ticketmanagement.model.enumeration.StaffType.SUPERVISOR;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final BusStaffService busStaffService;
    private final TripRepository tripRepository;
    private final RouteRepository routeRepository;

    public void createNewTrip(TripCreateRequest request) {
        Route route = routeRepository.findById(request.routeId())
                .orElseThrow(() -> {
                    log.error("No route found by id: {}", request.routeId());
                    return new ResourceNotFoundException(ROUTE_NOT_FOUND, "No route found by id: " + request.routeId());
                });

        BusStaff supervisor = busStaffService.findStaffById(request.supervisorId(), SUPERVISOR);
        BusStaff driver = busStaffService.findStaffById(request.driverId(), DRIVER);
        BusStaff helper = busStaffService.findStaffById(request.helperId(), HELPER);

        Trip trip = new Trip();
        trip.setJourneyDate(request.journeyDate());
        trip.setStartTime(request.startTime());
        trip.setCoachNumber(request.coachNumber());
        trip.setRoute(route);
        trip.setSupervisor(supervisor);
        trip.setDriver(driver);
        trip.setHelper(helper);
        trip.setFare(request.fare());

        tripRepository.save(trip);
    }

    public List<TripResponse> getAllTripsOfARoute(int routeId, LocalDate journeyDate, UUID idKey) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> {
                    log.error("No router found by id: {}", routeId);
                    return new ResourceNotFoundException(ROUTE_NOT_FOUND, "No router found by id: " + routeId);
                });

        if (!route.getIdKey().equals(idKey)) {
            throw new ActionNotPermittedException(ROUTE_NOT_PERMITTED, "Trip not permitted");
        }

        if (journeyDate.isBefore(LocalDate.now())) {
            throw new ActionNotPermittedException(INVALID_DATE, "Invalid date: " + journeyDate.toString());
        }

        List<Trip> trips = tripRepository
                .findByRouteAndJourneyDateAndActiveIsTrueOrderByStartTime(route, journeyDate);


        return getResponseFromTrip(trips);
    }

    public List<SeatResponse> getAllSeatsForATrip(long tripId, UUID idKey) {
        Trip trip = tripRepository.findTripFetchSeatsById(tripId)
                .orElseThrow(() -> {
                    log.error("No trip found by id: {}", tripId);
                    return new ResourceNotFoundException(TRIP_NOT_FOUND, "No trip found by id: " + tripId);
                });

        if (!trip.getIdKey().equals(idKey)) {
            throw new ActionNotPermittedException(TRIP_NOT_PERMITTED, "trip not permitted");
        }

        List<SeatResponse> response = getResponseFromSeats(trip.getSeats());
        response.sort(Comparator.comparingInt(SeatResponse::getOrdering));

        return response;
    }

    private List<TripResponse> getResponseFromTrip(List<Trip> trips) {
        List<TripResponse> list = new ArrayList<>(trips.size());
        for (Trip trip : trips) {
            list.add(tripToTripResponse(trip));
        }

        return list;
    }

    private List<SeatResponse> getResponseFromSeats(Collection<TicketSeat> seats) {
        List<SeatResponse> list = new ArrayList<SeatResponse>(seats.size());
        for (TicketSeat ticketSeat : seats) {
            list.add(ticketSeatToSeatResponse(ticketSeat));
        }

        return list;
    }

    private BusTypeResponse busTypeToBusTypeResponse(BusType busType) {
        BusType busType1 = null;

        BusTypeResponse busTypeResponse = new BusTypeResponse(busType1);

        busTypeResponse.setId(busType.getId());
        busTypeResponse.setName(busType.getName());
        busTypeResponse.setNumberOfSeats(busType.getNumberOfSeats());
        busTypeResponse.setNumberOfRows(busType.getNumberOfRows());

        return busTypeResponse;
    }

    protected TripResponse tripToTripResponse(Trip trip) {
        TripResponse tripResponse = new TripResponse();

        tripResponse.setId(trip.getId());
        tripResponse.setJourneyDate(trip.getJourneyDate());
        tripResponse.setStartTime(trip.getStartTime());
        if (trip.getIdKey() != null) {
            tripResponse.setIdKey(UUID.fromString(trip.getIdKey()));
        }
        tripResponse.setFare(trip.getFare());
        tripResponse.setBusType(busTypeToBusTypeResponse(trip.getBusType()));

        return tripResponse;
    }

    protected SeatResponse ticketSeatToSeatResponse(TicketSeat ticketSeat) {
        SeatResponse seatResponse = new SeatResponse();

        seatResponse.setId(ticketSeat.getId());
        seatResponse.setSeatNumber(ticketSeat.getSeatNumber());
        if (ticketSeat.getIdKey() != null) {
            seatResponse.setIdKey(UUID.fromString(ticketSeat.getIdKey()));
        }
        seatResponse.setOrdering(ticketSeat.getOrdering());
        seatResponse.setBooked(ticketSeat.isBooked());

        return seatResponse;
    }
}

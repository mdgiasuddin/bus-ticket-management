package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.model.dto.request.JourneyScheduleRequest;
import com.bus.online.ticketmanagement.model.entity.JourneySchedule;
import com.bus.online.ticketmanagement.model.entity.Route;
import com.bus.online.ticketmanagement.model.entity.Seat;
import com.bus.online.ticketmanagement.repository.JourneyScheduleRepository;
import com.bus.online.ticketmanagement.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.ROUTE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JourneyScheduleService {

    private final JourneyScheduleRepository journeyScheduleRepository;
    private final RouteRepository routeRepository;

    public void createNewJourneySchedule(JourneyScheduleRequest request) {
        Route route = routeRepository.findById(request.routeId())
                .orElseThrow(() -> new ResourceNotFoundException(ROUTE_NOT_FOUND));

        JourneySchedule schedule = new JourneySchedule();
        schedule.setJourneyDate(request.journeyDate());
        schedule.setStartTime(LocalTime.parse(request.startTime()));
        schedule.setCoachNumber(request.coachNumber());
        schedule.setRoute(route);
        schedule.setFare(request.fare());
        schedule.setBusType(request.busType());

        int rows = request.busType().getNumberOfRows();
        int cols = request.busType().getNumberOfSeats() / rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 1; j <= cols; j++) {
                Seat seat = new Seat();
                seat.setSeatNumber((char) ('A' + i) + "" + j);
                seat.setOrdering((short) (i * cols + j));
                seat.setJourneySchedule(schedule);

                schedule.getSeats().add(seat);
            }
        }

        journeyScheduleRepository.save(schedule);
    }
}

package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.exception.UniqueConstraintViolationException;
import com.bus.online.ticketmanagement.model.dto.request.StationRequest;
import com.bus.online.ticketmanagement.model.entity.Station;
import com.bus.online.ticketmanagement.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.STATION_ALREADY_EXISTS;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.STATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public void createNewStation(StationRequest request) {
        if (stationRepository.existsByName(request.name())) {
            throw new UniqueConstraintViolationException(STATION_ALREADY_EXISTS);
        }
        Station station = new Station();
        station.setName(request.name());
        station.setDistrict(request.district());

        stationRepository.save(station);
    }

    public void updateStation(StationRequest request) {
        Station station = stationRepository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(STATION_NOT_FOUND));

        if (
                !station.getName().equals(request.name()) &&
                        stationRepository.existsByName(request.name())
        ) {
            throw new UniqueConstraintViolationException(STATION_ALREADY_EXISTS);
        }

        station.setName(request.name());
        station.setDistrict(request.district());

        stationRepository.save(station);
    }
}

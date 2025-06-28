package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.model.dto.request.StationCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.StationUpdateRequest;
import com.bus.online.ticketmanagement.model.dto.response.StationResponse;
import com.bus.online.ticketmanagement.model.entity.Station;
import com.bus.online.ticketmanagement.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bus.online.ticketmanagement.constant.ExceptionCode.STATION_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<StationResponse> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(StationResponse::new)
                .toList();
    }

    public void createNewStation(StationCreateRequest request) {
        Station station = new Station();
        station.setName(request.name());
        station.setDistrict(request.district());

        stationRepository.save(station);
    }

    public void updateStation(StationUpdateRequest request) {
        Station station = stationRepository.findById(request.id())
                .orElseThrow(() -> {
                    log.error("No station found by id: {}", request.id());
                    return new ResourceNotFoundException(STATION_NOT_FOUND, "No station found by id: " + request.id());
                });

        station.setName(request.name());
        station.setDistrict(request.district());

        stationRepository.save(station);
    }
}

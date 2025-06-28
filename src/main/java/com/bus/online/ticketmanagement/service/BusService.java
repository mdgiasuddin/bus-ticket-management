package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ApplicationException;
import com.bus.online.ticketmanagement.model.dto.request.BusCreateRequest;
import com.bus.online.ticketmanagement.model.dto.request.BusTypeCreateRequest;
import com.bus.online.ticketmanagement.model.dto.response.BusResponse;
import com.bus.online.ticketmanagement.model.dto.response.BusTypeResponse;
import com.bus.online.ticketmanagement.model.entity.Bus;
import com.bus.online.ticketmanagement.model.entity.BusType;
import com.bus.online.ticketmanagement.repository.BusRepository;
import com.bus.online.ticketmanagement.repository.BusTypeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final BusTypeRepository busTypeRepository;

    public void createNewBus(@Valid BusCreateRequest request) {
        BusType busType = busTypeRepository.findById(request.busTypeId())
                .orElseThrow(() ->
                        {
                            log.error("Bus type not found by id: {}", request.busTypeId());
                            return new ApplicationException("BUS_TYPE_NOT_FOUND", "Bus type not found by id: " + request.busTypeId());
                        }
                );

        Bus bus = new Bus();
        bus.setRegistrationNumber(request.registrationNumber());
        bus.setBusType(busType);

        busRepository.save(bus);
    }

    public List<BusResponse> getAllBuses() {
        return busRepository.findAll()
                .stream()
                .map(BusResponse::new)
                .toList();
    }

    public List<BusTypeResponse> getAllBusTypes() {
        return busTypeRepository.findAll()
                .stream()
                .map(BusTypeResponse::new)
                .toList();
    }

    public void createNewBusType(@Valid BusTypeCreateRequest request) {
        BusType busType = new BusType();
        busType.setName(request.name());
        busType.setNumberOfRows(request.numberOfRows());
        busType.setNumberOfSeats(request.numberOfSeats());

        busTypeRepository.save(busType);
    }
}

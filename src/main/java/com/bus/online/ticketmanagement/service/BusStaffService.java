package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.model.entity.BusStaff;
import com.bus.online.ticketmanagement.model.enumeration.StaffType;
import com.bus.online.ticketmanagement.repository.BusStaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusStaffService {

    private final BusStaffRepository busStaffRepository;

    public BusStaff findStaffById(int id, StaffType type) {
        BusStaff busStaff = busStaffRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Bus staff not found by id: {}", id);
                    return new ResourceNotFoundException("BUS_STAFF_NOT_FOUND", "Bus staff not found by id: " + id);
                });

        if (busStaff.getType() != type) {
            log.error("Staff type: {} not match with: {}", busStaff.getType(), type);
            throw new ResourceNotFoundException("STAFF_TYPE_NOT_MATCH", "Staff type: " + busStaff.getType() + " not match with: " + type);
        }

        return busStaff;
    }
}

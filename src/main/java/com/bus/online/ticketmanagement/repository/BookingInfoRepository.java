package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingInfoRepository extends JpaRepository<BookingInfo, Long> {
}

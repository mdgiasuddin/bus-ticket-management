package com.bus.online.ticketmanagement.repository;

import com.bus.online.ticketmanagement.model.entity.RsaKeyPair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RsaKeyPairRepository extends JpaRepository<RsaKeyPair, String> {

    Optional<RsaKeyPair> findById(UUID id);

    void deleteByExpiryTimeBefore(LocalDateTime time);
}

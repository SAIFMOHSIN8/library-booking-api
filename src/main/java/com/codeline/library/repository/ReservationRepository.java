package com.codeline.library.repository;

import com.codeline.library.entity.Reservation;
import com.codeline.library.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    long countByResourceIdAndStatus(
            Long resourceId,
            ReservationStatus status
    );
}
package com.codeline.library.service;

import com.codeline.library.repository.BorrowRecordRepository;
import com.codeline.library.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import com.codeline.library.entity.BorrowRecord;
import com.codeline.library.entity.Reservation;
import com.codeline.library.enums.ReservationStatus;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            BorrowRecordRepository borrowRecordRepository) {

        this.reservationRepository = reservationRepository;
        this.borrowRecordRepository = borrowRecordRepository;

    }

    public Reservation claimReservation(Long reservationId, Long employeeId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getEmployee().getId().equals(employeeId)) {
            throw new RuntimeException(
                    "You cannot claim another employee's reservation"
            );
        }

        if (reservation.getExpiresAt().isBefore(LocalDateTime.now())) {

            reservation.setStatus(ReservationStatus.EXPIRED);

            reservationRepository.save(reservation);

            throw new RuntimeException("Reservation expired");
        }

        reservation.setStatus(ReservationStatus.CLAIMED);

        BorrowRecord borrowRecord = new BorrowRecord();

        borrowRecord.setEmployee(reservation.getEmployee());
        borrowRecord.setResource(reservation.getResource());

        borrowRecord.setBorrowedAt(LocalDateTime.now());
        borrowRecord.setActive(true);

        borrowRecordRepository.save(borrowRecord);

        return reservationRepository.save(reservation);
    }

}
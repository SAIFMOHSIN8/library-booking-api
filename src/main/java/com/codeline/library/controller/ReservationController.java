package com.codeline.library.controller;

import com.codeline.library.entity.Reservation;
import com.codeline.library.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/claim")
    public Reservation claimReservation(
            @RequestParam Long reservationId) {

        return reservationService.claimReservation(reservationId);
    }
}
package com.example.demo.reservation;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReservationRequest {
    private final Long parkingSpotId;
    private final LocalDateTime startOfReservation;
    private final LocalDateTime endOfReservation;
}

package com.example.demo.reservation;


import com.example.demo.IncorrectDataException;
import com.example.demo.ResourceTakenException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReservationValidator {
    public void checkAvailabilityOfParkingSpot(ReservationRequest reservationRequest, List<Reservation> listOfReservations) {

        var startOfNewReservation = reservationRequest.getStartOfReservation();
        var endOfNewReservation = reservationRequest.getEndOfReservation();
        if (startOfNewReservation.isAfter(endOfNewReservation))
            throw new IncorrectDataException("End of reservation is befor begining");
        checkAvailability(listOfReservations, startOfNewReservation, endOfNewReservation);
    }

    private void checkAvailability(List<Reservation> listOfReservations, LocalDateTime startOfNewReservation, LocalDateTime endOfNewReservation) {
        for (Reservation record : listOfReservations) {
            var start = record.getStartOfReservation();
            var end = record.getEndOfReservation();

            if (startOfNewReservation.isEqual(start) || endOfNewReservation.isEqual(end))
                throw new ResourceTakenException("You cannot start or end with this same time");


            if (start.isAfter(startOfNewReservation) && start.isBefore(endOfNewReservation)) {
                throw new ResourceTakenException("Begining of new reservation is inside other reservation");
            }

            if (end.isAfter(startOfNewReservation) && end.isBefore(endOfNewReservation)) {
                throw new ResourceTakenException("Ending of new reservation is inside other reservation");
            }

            if (startOfNewReservation.isAfter(start) && startOfNewReservation.isBefore(end)
                    && endOfNewReservation.isAfter(start) && endOfNewReservation.isBefore(end)) {
                throw new ResourceTakenException("Reservation request is inside other reservation");
            }
        }
    }
}

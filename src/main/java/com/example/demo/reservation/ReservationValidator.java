package com.example.demo.reservation;


import com.example.demo.IncorrectDataException;
import com.example.demo.ResourceTakenException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReservationValidator {
    public void checkAvailabilityOfParkingSpot(ReservationRequest reservationRequest, List<Reservation> listOfReservations) {
        if (reservationRequest.getStartOfReservation().isAfter(reservationRequest.getEndOfReservation()))
            throw new IncorrectDataException("End of reservation is before beginning");
        checkAvailability(listOfReservations, reservationRequest);
    }

    public void checkAvailabilityOfModifyingReservation(ReservationRequest reservationRequest, List<Reservation> listOfReservations, Long id) {
        if (reservationRequest.getStartOfReservation().isAfter(reservationRequest.getEndOfReservation()))
            throw new IncorrectDataException("End of reservation is before beginning");
        checkAvailabilityOfModifying(listOfReservations, reservationRequest, id);
    }

    private void checkAvailability(List<Reservation> listOfReservations, ReservationRequest reservationRequest) {
        var startOfNewReservation = reservationRequest.getStartOfReservation();
        var endOfNewReservation = reservationRequest.getEndOfReservation();
        for (Reservation record : listOfReservations) {
            checkConflictOfRecord(startOfNewReservation, endOfNewReservation, record);
        }
    }

    private void checkAvailabilityOfModifying(List<Reservation> listOfReservations, ReservationRequest reservationRequest, Long id) {
        var startOfNewReservation = reservationRequest.getStartOfReservation();
        var endOfNewReservation = reservationRequest.getEndOfReservation();
        for (Reservation record : listOfReservations) {
            if (!record.getId().equals(id))
                checkConflictOfRecord(startOfNewReservation, endOfNewReservation, record);
        }
    }

    private void checkConflictOfRecord(LocalDateTime startOfNewReservation, LocalDateTime endOfNewReservation, Reservation record) {
        var start = record.getStartOfReservation();
        var end = record.getEndOfReservation();

        if (startOfNewReservation.isEqual(start) || endOfNewReservation.isEqual(end)) {
            throw new ResourceTakenException("You cannot start or end with this same time");
        }

        if (start.isAfter(startOfNewReservation) && start.isBefore(endOfNewReservation)) {
            throw new ResourceTakenException("Beginning of other reservation is inside new  reservation");
        }

        if (end.isAfter(startOfNewReservation) && end.isBefore(endOfNewReservation)) {
            throw new ResourceTakenException("Ending of other reservation is inside new reservation");
        }

        if (startOfNewReservation.isAfter(start) && startOfNewReservation.isBefore(end)
                && endOfNewReservation.isAfter(start) && endOfNewReservation.isBefore(end)) {
            throw new ResourceTakenException("Reservation request is inside other reservation");
        }
    }


}

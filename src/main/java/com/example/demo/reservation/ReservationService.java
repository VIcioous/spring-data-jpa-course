package com.example.demo.reservation;


import com.example.demo.RecordNotFoundException;
import com.example.demo.UnauthorizedAccessException;
import com.example.demo.parkingSpot.ParkingSpot;
import com.example.demo.parkingSpot.ParkingSpotService;
import com.example.demo.user.AppUser;
import com.example.demo.user.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationValidator validator;
    private final ReservationRepository reservationRepository;
    private final ParkingSpotService parkingSpotService;


    public Long reserveParkingSpot(ReservationRequest reservationRequest) {
        ParkingSpot parkingSpot = getParkingSpot(reservationRequest.getParkingSpotId());
        List<Reservation> listOfReservations = reservationRepository.
                findReservationsOfParkingSpot(reservationRequest.getParkingSpotId());
        validator.checkAvailabilityOfParkingSpot(reservationRequest, listOfReservations);
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Reservation reservation = new Reservation();
        reservation.setUser(currentUser);
        setReservationData(reservationRequest, parkingSpot, reservation);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public void updateReservation(Long reservationId, ReservationRequest reservationRequest) {
        Reservation reservation = getReservationRecord(reservationId);
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authorizeAccess(currentUser, reservation);

        ParkingSpot parkingSpot = getParkingSpot(reservationRequest.getParkingSpotId());
        List<Reservation> listOfReservations = reservationRepository.
                findReservationsOfParkingSpot(reservationRequest.getParkingSpotId());
        validator.checkAvailabilityOfModifyingReservation(reservationRequest, listOfReservations, reservationId);
        setReservationData(reservationRequest, parkingSpot, reservation);
        reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = getReservationRecord(id);
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authorizeAccess(currentUser, reservation);
        reservationRepository.delete(reservation);
    }

    private void authorizeAccess(AppUser currentUser, Reservation reservation) {
        if (currentUser.getUserType() != UserType.ADMIN) {
            if (!currentUser.getId().equals(reservation.getUser().getId())) {
                throw new UnauthorizedAccessException("Gdzie kurwa leziesz");
            }
        }
    }

    private Reservation getReservationRecord(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Reservation Not found"));
    }

    private ParkingSpot getParkingSpot(Long id) {
        return parkingSpotService.get(id)
                .orElseThrow(() -> new RecordNotFoundException("Parking Spot not found"));
    }

    private void setReservationData(ReservationRequest reservationRequest, ParkingSpot parkingSpot, Reservation reservation) {

        reservation.setStartOfReservation(reservationRequest.getStartOfReservation());
        reservation.setEndOfReservation(reservationRequest.getEndOfReservation());
        reservation.setParkingSpot(parkingSpot);
        reservation.setConfirmationExpireTime(reservationRequest.getStartOfReservation().plusMinutes(60L));
    }

}

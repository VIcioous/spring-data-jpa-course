package com.example.demo.reservation;

import com.example.demo.parkingSpot.ParkingSpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


class ReservationValidatorTest {

    private static List<Reservation> reservationSet = new ArrayList<>();
    private ReservationValidator validator = new ReservationValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "CorrectReservation.csv")
    @DisplayName("This reservation should pass correctly")
    public void shouldPassValidationOfReservation(Long spotId, String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);
        ReservationRequest request = new ReservationRequest(spotId, s, e);

        Assertions.assertDoesNotThrow(() -> validator.checkAvailabilityOfParkingSpot(request, reservationSet));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CorrectModification.csv")
    @DisplayName("This modification should pass correctly")
    public void shouldPassModifyingReservation(Long reservationId, String start, String end, Long spotId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);
        ReservationRequest request = new ReservationRequest(spotId, s, e);

        Assertions.assertDoesNotThrow(
                () -> validator.checkAvailabilityOfModifyingReservation(request, reservationSet, reservationId));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "IncorrectReservation.csv")
    @DisplayName("This Reservation should not pass")
    public void shouldThrowResponseStatusExceptionWhenReserving(Long spotId, String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);
        ReservationRequest request = new ReservationRequest(spotId, s, e);

        Assertions.assertThrows(ResponseStatusException.class,
                () -> validator.checkAvailabilityOfParkingSpot(request, reservationSet));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "IncorrectModification.csv")
    @DisplayName("This modification should not pass")
    public void shouldThrowResponseStatusExceptionWhenModifying(Long reservationId, String start, String end, Long spotID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);
        ReservationRequest request = new ReservationRequest(spotID, s, e);

        Assertions.assertThrows(ResponseStatusException.class,
                () -> validator.checkAvailabilityOfModifyingReservation(request, reservationSet, reservationId));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "ReversedDates.csv")
    @DisplayName("This modification should not pass because date is reversed")
    public void shouldThrowIncorrectDataException(Long reservationId, String start, String end, Long spotID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);
        ReservationRequest request = new ReservationRequest(spotID, s, e);

        Assertions.assertThrows(ResponseStatusException.class,
                () -> validator.checkAvailabilityOfModifyingReservation(request, reservationSet, reservationId));
        Assertions.assertThrows(ResponseStatusException.class,
                () -> validator.checkAvailabilityOfParkingSpot(request, reservationSet));
    }


    @BeforeAll
    static void initAll() {
        String row;
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setName("Spot1");
        parkingSpot.setId(1L);
        Long id = 0L;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("test/DataResources.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Reservation reservation = new Reservation();
                reservation.setParkingSpot(parkingSpot);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                reservation.setStartOfReservation(LocalDateTime.parse(data[0], formatter));
                reservation.setEndOfReservation(LocalDateTime.parse(data[1], formatter));
                reservation.setId(id);
                id++;
                reservationSet.add(reservation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
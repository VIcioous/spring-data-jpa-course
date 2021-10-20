package com.example.demo.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@RestController
@EnableSwagger2
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/Reservation")
    @ResponseStatus(HttpStatus.OK)
    public Long reserveParkingSpot(@RequestBody ReservationRequest request) {
        return reservationService.reserveParkingSpot(request);
    }

    @PutMapping("/Reservation")
    @ResponseStatus(HttpStatus.OK)
    public void updateReservation(@RequestBody ReservationRequest request, @RequestParam Long id) {
        reservationService.updateReservation(id, request);
    }

    @DeleteMapping("/Reservation")
    @ResponseStatus(HttpStatus.OK)
    public void cancelReservation(@RequestParam Long id) {
        reservationService.cancelReservation(id);
    }

    @GetMapping("/Reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Reservation> getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/Reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservations() {
        return reservationService.getReservations();
    }

    @PutMapping("/Reservations/Confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmReservation(@PathVariable Long id)
    {
        reservationService.confirmReservation(id);
    }

    @PutMapping("/Reservations/Report/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void reportReservation(@PathVariable Long id)
    {
        reservationService.reportTakenReservation(id);
    }
}

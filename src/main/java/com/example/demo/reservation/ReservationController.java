package com.example.demo.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class ReservationController {

    private final  ReservationService reservationService;


    @PostMapping("/Reservation")
    @ResponseStatus(HttpStatus.OK)
    public Long reserveParkingSpot(@RequestBody ReservationRequest request) {
        reservationService.reserveParkingSpot(request);
        return null;
    }
}

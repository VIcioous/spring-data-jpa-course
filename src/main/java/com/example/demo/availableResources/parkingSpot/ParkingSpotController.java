package com.example.demo.availableResources.parkingSpot;


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
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @PostMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public Long addParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO) {
        return parkingSpotService.addParkingSpot(parkingSpotDTO);
    }

    @PutMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public void updateParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO, @RequestParam Long id) {
        parkingSpotService.updateParkingSpot(parkingSpotDTO, id);
    }

    @DeleteMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParkingSpot(@RequestParam Long id) {
        parkingSpotService.deleteParkingSpot(id);
    }

    @GetMapping("/ParkingSpot/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ParkingSpotRecord> getParkingSpot(@PathVariable Long id) {
        return parkingSpotService.getParkingSpot(id);
    }

    @GetMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingSpotRecord> getAllParkingSpot() {
        return parkingSpotService.getParkingSpots();
    }
}

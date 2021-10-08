package com.example.demo.organizationUnit.parkingSpot;


import com.example.demo.organizationUnit.AssignableResourceService;
import com.example.demo.organizationUnit.ResourceToUnitAssignmentData;
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

    private final AssignableResourceService<ParkingSpotDTO, ParkingSpot> parkingSpotService;

    @PostMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public Long addParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO) {
        return parkingSpotService.add(parkingSpotDTO);
    }

    @PutMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public void updateParkingSpot(@RequestBody ParkingSpotDTO parkingSpotDTO, @RequestParam Long id) {
        parkingSpotService.update(parkingSpotDTO, id);
    }

    @DeleteMapping("/ParkingSpot")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParkingSpot(@RequestParam Long id) {
        parkingSpotService.delete(id);
    }

    @GetMapping("/ParkingSpot/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ParkingSpot> getParkingSpot(@PathVariable Long id) {
        return parkingSpotService.get(id);
    }

    @GetMapping("/ParkingSpot/")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotService.getAll();
    }

    @PutMapping("/ParkingSpot/Assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignParkingSpotToUnit(@RequestBody ResourceToUnitAssignmentData assignmentData) {
        parkingSpotService.assignToUnit(assignmentData);
    }


}

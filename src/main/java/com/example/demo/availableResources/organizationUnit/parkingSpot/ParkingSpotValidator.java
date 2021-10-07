package com.example.demo.availableResources.organizationUnit.parkingSpot;


import com.example.demo.availableResources.IncorrectDataException;
import org.springframework.stereotype.Service;


@Service
public class ParkingSpotValidator {
    public void validateParkingSpotDTO(ParkingSpotDTO parkingSpotDTO) {
        checkNameOfDesk(parkingSpotDTO.getName());
    }

    private void checkNameOfDesk(String name) {
        if (name.isBlank()) throw new IncorrectDataException("Field Name is empty");
    }
}

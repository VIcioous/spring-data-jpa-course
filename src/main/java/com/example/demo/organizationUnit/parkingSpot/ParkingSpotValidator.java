package com.example.demo.organizationUnit.parkingSpot;


import com.example.demo.IncorrectDataException;
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

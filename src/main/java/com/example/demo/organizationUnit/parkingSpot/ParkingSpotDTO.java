package com.example.demo.organizationUnit.parkingSpot;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class ParkingSpotDTO {
    private String name;
    @Enumerated(EnumType.STRING)
    private ParkingType type;
}

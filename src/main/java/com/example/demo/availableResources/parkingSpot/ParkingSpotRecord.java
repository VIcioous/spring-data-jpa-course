package com.example.demo.availableResources.parkingSpot;


import com.example.demo.availableResources.organizationUnit.OrganizationUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "parking_Spot")
@Entity
public class ParkingSpotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ParkingType type;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "organization_unit_id",nullable = false)
    private OrganizationUnit organizationUnit;
}


package com.example.demo.organizationUnit;



import com.example.demo.organizationUnit.parkingSpot.ParkingSpot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "organization_unit")
public class OrganizationUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "organizationUnit")
    private Set<ParkingSpot> parkingSpots = new HashSet<>();

}

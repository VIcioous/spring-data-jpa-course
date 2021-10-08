package com.example.demo.organizationUnit.parkingSpot;


import com.example.demo.organizationUnit.OrganizationUnit;
import com.example.demo.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "parking_Spot")
@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private ParkingType type;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_unit_id", referencedColumnName = "id")
    private OrganizationUnit organizationUnit;

    @OneToMany(mappedBy = "parkingSpot")
    private Set<Reservation> listOfReservations = new HashSet<>();
}


package com.example.demo.parkingSpot;


import com.example.demo.reservation.Reservation;
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

    @OneToMany(mappedBy = "parkingSpot")
    private Set<Reservation> listOfReservations = new HashSet<>();
}


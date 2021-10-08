package com.example.demo.reservation;

import com.example.demo.organizationUnit.parkingSpot.ParkingSpot;
import com.example.demo.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "reservation")
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startOfReservation;
    private LocalDateTime endOfReservation;
    private LocalDateTime confirmationExpireTime;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private AppUser user;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_spot_record_id",referencedColumnName = "id")
    private ParkingSpot parkingSpot;
    private boolean confirmed;

}

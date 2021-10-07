package com.example.demo.availableResources.organizationUnit;


import com.example.demo.availableResources.organizationUnit.conferenceRoom.ConferenceRoomRecord;
import com.example.demo.availableResources.organizationUnit.desk.DeskRecord;
import com.example.demo.availableResources.organizationUnit.parkingSpot.ParkingSpotRecord;
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

    @JsonIgnore
    @OneToMany(mappedBy = "organizationUnit")
    private Set<ConferenceRoomRecord> conferenceRooms = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "organizationUnit")
    private Set<DeskRecord> desks = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "organizationUnit")
    private Set<ParkingSpotRecord> parkingSpots = new HashSet<>();

}

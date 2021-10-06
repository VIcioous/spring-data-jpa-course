package com.example.demo.availableResources.organizationUnit;



import com.example.demo.availableResources.conferenceRoom.ConferenceRoomRecord;
import com.example.demo.availableResources.desk.DeskRecord;
import com.example.demo.availableResources.parkingSpot.ParkingSpotRecord;
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

    private Long id;
    private String title;

    @OneToMany(mappedBy = "organizationUnit")
    private Set<ConferenceRoomRecord> conferenceRooms = new HashSet<>();

    @OneToMany(mappedBy = "organizationUnit")
    private Set<DeskRecord> desks = new HashSet<>();

    @OneToMany(mappedBy = "organizationUnit")
    private Set<ParkingSpotRecord> parkingSpots = new HashSet<>();

}

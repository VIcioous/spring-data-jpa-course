package com.example.demo.availableResources.organizationUnit.conferenceRoom;


import com.example.demo.availableResources.organizationUnit.OrganizationUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "conference_Room")
@Entity
public class ConferenceRoomRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String numberOfSeats;
    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_unit_id", referencedColumnName = "id")
    private OrganizationUnit organizationUnit;
}


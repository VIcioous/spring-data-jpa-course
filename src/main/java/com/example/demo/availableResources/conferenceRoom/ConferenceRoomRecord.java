package com.example.demo.availableResources.conferenceRoom;


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
}


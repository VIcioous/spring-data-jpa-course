package com.example.demo.reports;

import com.example.demo.reservation.Reservation;
import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timeOfReport;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user ;

    Report(Reservation reservation, AppUser user)
    {
        new Report(reservation);
        this.user=user;
    }
    Report(Reservation reservation)
    {
        this.timeOfReport = LocalDateTime.now();
        this.reservation=reservation;
    }



}

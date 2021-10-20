package com.example.demo.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {



    @Query("SELECT r FROM Reservation r WHERE r.parkingSpot.id= ?1")
    List<Reservation> findReservationsOfParkingSpot(Long id);
}

package com.example.demo.availableResources.organizationUnit.parkingSpot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotRecord, Long> {
}

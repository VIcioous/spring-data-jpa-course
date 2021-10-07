package com.example.demo.availableResources.organizationUnit.conferenceRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoomRecord, Long> {
}

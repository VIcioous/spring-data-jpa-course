package com.example.demo.availableResources.parkingSpot;

import com.example.demo.availableResources.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ParkingSpotService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Resource");
    private final EntityManager entityManager = emf.createEntityManager();
    private final ParkingSpotValidator validator;


    public Long addParkingSpot(ParkingSpotDTO parkingSpotDTO) {
        entityManager.getTransaction().begin();
        validator.validateParkingSpotDTO(parkingSpotDTO);

        ParkingSpotRecord parkingSpotRecord = new ParkingSpotRecord();
        parkingSpotRecord.setAvailable(true);
        parkingSpotRecord.setName(parkingSpotDTO.getName());
        parkingSpotRecord.setType(parkingSpotDTO.getType());

        entityManager.persist(parkingSpotRecord);
        entityManager.getTransaction().commit();
        return parkingSpotRecord.getId();
    }

    public void updateParkingSpot(ParkingSpotDTO parkingSpotDTO, Long id) {
        validator.validateParkingSpotDTO(parkingSpotDTO);
        ParkingSpotRecord record = entityManager.find(ParkingSpotRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            record.setName(parkingSpotDTO.getName());
            record.setType(parkingSpotDTO.getType());
            entityManager.getTransaction().commit();
        } else throw new RecordNotFoundException("Record Not Found");
    }

    public void deleteParkingSpot(Long id) {
        ParkingSpotRecord record = entityManager.find(ParkingSpotRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(record);
            entityManager.getTransaction().commit();
        }
    }

    public Optional<ParkingSpotRecord> getParkingSpot(Long id) {
        ParkingSpotRecord record = entityManager.find(ParkingSpotRecord.class, id);
        return Optional.ofNullable(record);
    }

    public List<ParkingSpotRecord> getParkingSpots() {
        return entityManager.createQuery("from ParkingSpotRecord").getResultList();
    }
}

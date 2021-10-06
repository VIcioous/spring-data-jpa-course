package com.example.demo.availableResources.conferenceRoom;

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
public class ConferenceRoomService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Resource");
    private final EntityManager entityManager = emf.createEntityManager();
    private final ConferenceRoomValidator validator;


    public Long addConferenceRoom(ConferenceRoomDTO conferenceRoomDTO) {

        entityManager.getTransaction().begin();
        validator.validateConferenceRoomDTO(conferenceRoomDTO);
        ConferenceRoomRecord conferenceRoomRecord = new ConferenceRoomRecord();
        conferenceRoomRecord.setAvailable(true);
        conferenceRoomRecord.setNumberOfSeats(conferenceRoomDTO.getNumberOfSeats());
        conferenceRoomRecord.setName(conferenceRoomDTO.getName());
        entityManager.persist(conferenceRoomRecord);
        entityManager.getTransaction().commit();
        return conferenceRoomRecord.getId();

    }

    public void updateConferenceRoom(ConferenceRoomDTO conferenceRoomDTO, Long id) {
        validator.validateConferenceRoomDTO(conferenceRoomDTO);
        ConferenceRoomRecord record = entityManager.find(ConferenceRoomRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            record.setName(conferenceRoomDTO.getName());
            record.setNumberOfSeats(conferenceRoomDTO.getNumberOfSeats());
            entityManager.getTransaction().commit();
        } else throw new RecordNotFoundException("Record Not Found");
    }

    public void deleteConferenceRoom(Long id) {
        ConferenceRoomRecord record = entityManager.find(ConferenceRoomRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(record);
            entityManager.getTransaction().commit();
        }
    }

    public Optional<ConferenceRoomRecord> getConferenceRoom(Long id) {
        ConferenceRoomRecord record = entityManager.find(ConferenceRoomRecord.class, id);
        return Optional.ofNullable(record);
    }

    public List<ConferenceRoomRecord> getAllConferenceRoom() {
        return entityManager.createQuery("from ConferenceRoomRecord").getResultList();
    }
}

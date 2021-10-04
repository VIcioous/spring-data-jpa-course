package com.example.demo.availableResources.desk;

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
public class DeskService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Resource");
    private final EntityManager entityManager = emf.createEntityManager();
    private final DeskValidator validator;


    public Long addDesk(DeskDTO deskDTO) {
        entityManager.getTransaction().begin();
        validator.validateDeskDTO(deskDTO);

        DeskRecord deskRecord = new DeskRecord();
        deskRecord.setAvailable(true);
        deskRecord.setName(deskDTO.getName());

        entityManager.persist(deskRecord);
        entityManager.getTransaction().commit();
        return deskRecord.getId();
    }

    public void updateDesk(DeskDTO deskDTO, Long id) {
        validator.validateDeskDTO(deskDTO);
        DeskRecord record = entityManager.find(DeskRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            record.setName(deskDTO.getName());
            entityManager.getTransaction().commit();
        } else throw new RecordNotFoundException("Record Not Found");
    }

    public void deleteDesk(Long id) {
        DeskRecord record = entityManager.find(DeskRecord.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(record);
            entityManager.getTransaction().commit();
        }
    }

    public Optional<DeskRecord> getDesk(Long id) {
        DeskRecord record = entityManager.find(DeskRecord.class, id);
        return Optional.ofNullable(record);
    }

    public List<DeskRecord> getAllDesks() {
        return entityManager.createQuery("from DeskRecord").getResultList();
    }
}

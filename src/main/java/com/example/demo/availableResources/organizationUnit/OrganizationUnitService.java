package com.example.demo.availableResources.organizationUnit;


import com.example.demo.availableResources.RecordNotFoundException;
import com.example.demo.availableResources.conferenceRoom.ConferenceRoomRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationUnitService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Resource");
    private final EntityManager entityManager = emf.createEntityManager();
    private final OrganizationUnitValidator validator;

    public Long addOrganizationUnit(OrganizationUnitDTO organizationUnitDTO) {
        entityManager.getTransaction().begin();
        validator.validateOrganizationUnitDTO(organizationUnitDTO);
        OrganizationUnit record = new OrganizationUnit();
        record.setTitle(organizationUnitDTO.getName());
        entityManager.persist(record);
        entityManager.getTransaction().commit();
        return record.getId();
    }

    public void updateOrganizationUnit(OrganizationUnitDTO organizationUnitDTO, Long id) {
        validator.validateOrganizationUnitDTO(organizationUnitDTO);
        OrganizationUnit record = entityManager.find(OrganizationUnit.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            record.setTitle(organizationUnitDTO.getName());
            entityManager.getTransaction().commit();
        } else throw new RecordNotFoundException("Record Not Found");
    }

    public void deleteOrganizationUnit(Long id) {
        OrganizationUnit record = entityManager.find(OrganizationUnit.class, id);
        if (record != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(record);
            entityManager.getTransaction().commit();
        }
    }

    public Optional<OrganizationUnit> getOrganizationUnit(Long id) {
        OrganizationUnit record = entityManager.find(OrganizationUnit.class, id);
        return Optional.ofNullable(record);
    }

    public List<OrganizationUnit> getAllOrganizationUnits() {
        return entityManager.createQuery("from OrganizationUnit").getResultList();
    }
}

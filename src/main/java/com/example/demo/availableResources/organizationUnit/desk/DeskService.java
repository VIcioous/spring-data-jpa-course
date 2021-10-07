package com.example.demo.availableResources.organizationUnit.desk;

import com.example.demo.availableResources.RecordNotFoundException;
import com.example.demo.availableResources.organizationUnit.AssignableResourceService;
import com.example.demo.availableResources.organizationUnit.OrganizationUnit;
import com.example.demo.availableResources.organizationUnit.OrganizationUnitRepository;
import com.example.demo.availableResources.organizationUnit.ResourceToUnitAssignmentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class DeskService implements AssignableResourceService<DeskDTO, DeskRecord> {
    private final DeskRepository deskRepository;
    private final OrganizationUnitRepository organizationUnitRepository;
    private final DeskValidator validator;

    @Override
    public Long add(DeskDTO deskDTO) {
        validator.validateDeskDTO(deskDTO);
        DeskRecord deskRecord = new DeskRecord();
        deskRecord.setAvailable(true);
        deskRecord.setName(deskDTO.getName());
        deskRepository.save(deskRecord);
        return deskRecord.getId();
    }

    @Override
    public void update(DeskDTO deskDTO, Long id) {
        validator.validateDeskDTO(deskDTO);
        DeskRecord record = getDeskFromRepository(id);
        record.setName(deskDTO.getName());
        deskRepository.save(record);
    }

    @Override
    public void delete(Long id) {
        DeskRecord record = getDeskFromRepository(id);
        deskRepository.delete(record);
    }

    @Override
    public Optional<DeskRecord> get(Long id) {
        return deskRepository.findById(id);
    }

    @Override
    public List<DeskRecord> getAll() {
        return deskRepository.findAll();
    }

    @Override
    public void assignToUnit(ResourceToUnitAssignmentData assignmentData) {
        DeskRecord desk = getDeskFromRepository(assignmentData.getResourceId());
        OrganizationUnit unit = organizationUnitRepository.findById(assignmentData.getUnitId())
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"));
        desk.setOrganizationUnit(unit);
        deskRepository.save(desk);
    }

    private DeskRecord getDeskFromRepository(Long id) {
        return deskRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Desk Not Found"));
    }
}

package com.example.demo.organizationUnit;

import com.example.demo.RecordNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationUnitService {
    private final OrganizationUnitRepository organizationUnitRepository;
    private final OrganizationUnitValidator validator;

    public Long addOrganizationUnit(OrganizationUnitDTO organizationUnitDTO) {
        validator.validateOrganizationUnitDTO(organizationUnitDTO);
        OrganizationUnit record = new OrganizationUnit();
        record.setTitle(organizationUnitDTO.getName());
        organizationUnitRepository.save(record);
        return record.getId();
    }

    public void updateOrganizationUnit(OrganizationUnitDTO organizationUnitDTO, Long id) {
        validator.validateOrganizationUnitDTO(organizationUnitDTO);
        OrganizationUnit record = findOrganizationUnitInRepository(id);
        record.setTitle(organizationUnitDTO.getName());
        organizationUnitRepository.save(record);
    }

    public void deleteOrganizationUnit(Long id) {
        OrganizationUnit record = findOrganizationUnitInRepository(id);
        organizationUnitRepository.delete(record);
    }

    public Optional<OrganizationUnit> getOrganizationUnit(Long id) {
        return organizationUnitRepository.findById(id);
    }

    public List<OrganizationUnit> getAllOrganizationUnits() {
        return organizationUnitRepository.findAll();
    }

    private OrganizationUnit findOrganizationUnitInRepository(Long id) {
        return organizationUnitRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Not Found Organization Unit"));
    }


}

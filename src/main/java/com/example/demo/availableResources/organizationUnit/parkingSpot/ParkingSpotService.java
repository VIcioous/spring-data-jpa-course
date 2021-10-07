package com.example.demo.availableResources.organizationUnit.parkingSpot;

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
public class ParkingSpotService implements AssignableResourceService<ParkingSpotDTO, ParkingSpotRecord> {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotValidator validator;
    private final OrganizationUnitRepository organizationUnitRepository;

    @Override
    public Long add(ParkingSpotDTO parkingSpotDTO) {
        validator.validateParkingSpotDTO(parkingSpotDTO);
        ParkingSpotRecord parkingSpotRecord = new ParkingSpotRecord();
        parkingSpotRecord.setAvailable(true);
        parkingSpotRecord.setName(parkingSpotDTO.getName());
        parkingSpotRecord.setType(parkingSpotDTO.getType());
        parkingSpotRepository.save(parkingSpotRecord);
        return parkingSpotRecord.getId();
    }

    @Override
    public void update(ParkingSpotDTO parkingSpotDTO, Long id) {
        validator.validateParkingSpotDTO(parkingSpotDTO);
        ParkingSpotRecord record = getParkingSpotFromRepository(id);
        record.setName(parkingSpotDTO.getName());
        record.setType(parkingSpotDTO.getType());
    }

    @Override
    public void delete(Long id) {
        ParkingSpotRecord record = getParkingSpotFromRepository(id);
        parkingSpotRepository.delete(record);
    }

    @Override
    public Optional<ParkingSpotRecord> get(Long id) {
        return parkingSpotRepository.findById(id);
    }

    @Override
    public List<ParkingSpotRecord> getAll() {
        return parkingSpotRepository.findAll();
    }

    @Override
    public void assignToUnit(ResourceToUnitAssignmentData assignmentData) {
        ParkingSpotRecord parkingSpot = getParkingSpotFromRepository(assignmentData.getResourceId());
        OrganizationUnit unit = organizationUnitRepository.findById(assignmentData.getUnitId())
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"));
        parkingSpot.setOrganizationUnit(unit);
        parkingSpotRepository.save(parkingSpot);
    }

    private ParkingSpotRecord getParkingSpotFromRepository(Long id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Parking Spot not Found"));
    }
}

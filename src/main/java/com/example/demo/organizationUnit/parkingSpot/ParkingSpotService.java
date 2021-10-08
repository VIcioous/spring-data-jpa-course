package com.example.demo.organizationUnit.parkingSpot;

import com.example.demo.RecordNotFoundException;
import com.example.demo.organizationUnit.AssignableResourceService;
import com.example.demo.organizationUnit.OrganizationUnit;
import com.example.demo.organizationUnit.OrganizationUnitRepository;
import com.example.demo.organizationUnit.ResourceToUnitAssignmentData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ParkingSpotService implements AssignableResourceService<ParkingSpotDTO, ParkingSpot> {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotValidator validator;
    private final OrganizationUnitRepository organizationUnitRepository;

    @Override
    public Long add(ParkingSpotDTO parkingSpotDTO) {
        validator.validateParkingSpotDTO(parkingSpotDTO);
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setName(parkingSpotDTO.getName());
        parkingSpot.setType(parkingSpotDTO.getType());
        parkingSpotRepository.save(parkingSpot);
        return parkingSpot.getId();
    }

    @Override
    public void update(ParkingSpotDTO parkingSpotDTO, Long id) {
        validator.validateParkingSpotDTO(parkingSpotDTO);
        ParkingSpot record = getParkingSpotFromRepository(id);
        record.setName(parkingSpotDTO.getName());
        record.setType(parkingSpotDTO.getType());
    }

    @Override
    public void delete(Long id) {
        ParkingSpot record = getParkingSpotFromRepository(id);
        parkingSpotRepository.delete(record);
    }

    @Override
    public Optional<ParkingSpot> get(Long id) {
        return parkingSpotRepository.findById(id);
    }

    @Override
    public List<ParkingSpot> getAll() {
        return parkingSpotRepository.findAll();
    }

    @Override
    public void assignToUnit(ResourceToUnitAssignmentData assignmentData) {
        ParkingSpot parkingSpot = getParkingSpotFromRepository(assignmentData.getResourceId());
        OrganizationUnit unit = organizationUnitRepository.findById(assignmentData.getUnitId())
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"));
        parkingSpot.setOrganizationUnit(unit);
        parkingSpotRepository.save(parkingSpot);
    }

    private ParkingSpot getParkingSpotFromRepository(Long id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Parking Spot not Found"));
    }
}

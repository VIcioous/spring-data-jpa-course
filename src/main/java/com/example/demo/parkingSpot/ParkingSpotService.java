package com.example.demo.parkingSpot;

import com.example.demo.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ParkingSpotService implements AssignableResourceService<ParkingSpotDTO, ParkingSpot> {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotValidator validator;


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

    private ParkingSpot getParkingSpotFromRepository(Long id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Parking Spot not Found"));
    }
}

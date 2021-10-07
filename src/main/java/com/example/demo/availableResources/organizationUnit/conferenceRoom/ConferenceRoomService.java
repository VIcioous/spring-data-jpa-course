package com.example.demo.availableResources.organizationUnit.conferenceRoom;

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
public class ConferenceRoomService implements AssignableResourceService<ConferenceRoomDTO, ConferenceRoomRecord> {

    private final ConferenceRoomValidator validator;
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationUnitRepository organizationUnitRepository;

    @Override
    public Long add(ConferenceRoomDTO conferenceRoomDTO) {
        validator.validateConferenceRoomDTO(conferenceRoomDTO);
        ConferenceRoomRecord conferenceRoomRecord = new ConferenceRoomRecord();
        conferenceRoomRecord.setAvailable(true);
        conferenceRoomRecord.setNumberOfSeats(conferenceRoomDTO.getNumberOfSeats());
        conferenceRoomRecord.setName(conferenceRoomDTO.getName());
        conferenceRoomRepository.save(conferenceRoomRecord);
        return conferenceRoomRecord.getId();
    }

    @Override
    public void update(ConferenceRoomDTO conferenceRoomDTO, Long id) {
        validator.validateConferenceRoomDTO(conferenceRoomDTO);
        ConferenceRoomRecord record = getConferenceRoomFromRepository(id);
        record.setName(conferenceRoomDTO.getName());
        record.setNumberOfSeats(conferenceRoomDTO.getNumberOfSeats());
        conferenceRoomRepository.save(record);
    }

    @Override
    public void delete(Long id) {
        ConferenceRoomRecord record = getConferenceRoomFromRepository(id);
        conferenceRoomRepository.delete(record);
    }

    @Override
    public Optional<ConferenceRoomRecord> get(Long id) {
        return conferenceRoomRepository.findById(id);
    }

    @Override
    public List<ConferenceRoomRecord> getAll() {
        return conferenceRoomRepository.findAll();
    }

    @Override
    public void assignToUnit(ResourceToUnitAssignmentData assignmentData) {
        ConferenceRoomRecord conferenceRoom = getConferenceRoomFromRepository(assignmentData.getResourceId());
        OrganizationUnit unit = organizationUnitRepository.findById(assignmentData.getUnitId())
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found"));
        conferenceRoom.setOrganizationUnit(unit);
        conferenceRoomRepository.save(conferenceRoom);
    }

    private ConferenceRoomRecord getConferenceRoomFromRepository(Long id) {
        return conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Conference Room not found"));
    }
}

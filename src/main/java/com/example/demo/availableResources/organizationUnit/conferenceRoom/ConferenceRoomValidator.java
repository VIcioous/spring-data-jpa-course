package com.example.demo.availableResources.organizationUnit.conferenceRoom;


import com.example.demo.availableResources.IncorrectDataException;
import org.springframework.stereotype.Service;


@Service
public class ConferenceRoomValidator {
    public void validateConferenceRoomDTO(ConferenceRoomDTO conferenceRoomDTO) {
        checkNameOfRoom(conferenceRoomDTO.getName());
        checkNumberOfSeats(conferenceRoomDTO);
    }

    private void checkNumberOfSeats(ConferenceRoomDTO conferenceRoomDTO) {
        if (isNotNumeric(conferenceRoomDTO.getNumberOfSeats())) throw new IncorrectDataException("Given data is NaN");
        if (Integer.parseInt(conferenceRoomDTO.getNumberOfSeats()) <= 0)
            throw new IncorrectDataException("Incorrect number of seats");
    }

    private void checkNameOfRoom(String name) {
        if (name.isBlank()) throw new IncorrectDataException("Field Name is empty");
    }

    private boolean isNotNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
}

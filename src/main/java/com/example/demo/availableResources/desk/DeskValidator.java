package com.example.demo.availableResources.desk;


import com.example.demo.availableResources.IncorrectDataException;
import org.springframework.stereotype.Service;


@Service
public class DeskValidator {
    public void validateDeskDTO(DeskDTO deskDTO) {
        checkNameOfDesk(deskDTO.getName());
    }

    private void checkNameOfDesk(String name) {
        if (name.isBlank()) throw new IncorrectDataException("Field Name is empty");
    }
}

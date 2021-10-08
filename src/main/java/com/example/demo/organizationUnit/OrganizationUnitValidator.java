package com.example.demo.organizationUnit;

import com.example.demo.IncorrectDataException;
import org.springframework.stereotype.Service;

@Service
public class OrganizationUnitValidator {
    public void validateOrganizationUnitDTO(OrganizationUnitDTO organizationUnitDTO) {
        checkNameOfDesk(organizationUnitDTO.getName());
    }

    private void checkNameOfDesk(String name) {
        if (name.isBlank()) throw new IncorrectDataException("Field Name is empty");
    }
}

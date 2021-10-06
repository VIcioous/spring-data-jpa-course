package com.example.demo.availableResources.organizationUnit;

import com.example.demo.availableResources.IncorrectDataException;

public class OrganizationUnitValidator {
    public void validateOrganizationUnitDTO(OrganizationUnitDTO organizationUnitDTO) {
        checkNameOfDesk(organizationUnitDTO.getName());
    }

    private void checkNameOfDesk(String name) {
        if (name.isBlank()) throw new IncorrectDataException("Field Name is empty");
    }
}

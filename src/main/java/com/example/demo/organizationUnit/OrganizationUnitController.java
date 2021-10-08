package com.example.demo.organizationUnit;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;


@RestController
@EnableSwagger2
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class OrganizationUnitController {

    private final OrganizationUnitService organizationUnitService;

    @PostMapping("/Unit")
    @ResponseStatus(HttpStatus.OK)
    public Long addOrganizationUnit(@RequestBody OrganizationUnitDTO organizationUnitDTO) {
        return organizationUnitService.addOrganizationUnit(organizationUnitDTO);
    }

    @PutMapping("/Unit")
    @ResponseStatus(HttpStatus.OK)
    public void updateUnit(@RequestBody OrganizationUnitDTO organizationUnitDTO, @PathVariable Long id) {
        organizationUnitService.updateOrganizationUnit(organizationUnitDTO, id);
    }

    @DeleteMapping("/Unit")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUnit(@RequestParam Long id) {
        organizationUnitService.deleteOrganizationUnit(id);
    }

    @GetMapping("/Unit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<OrganizationUnit> getUnit(@PathVariable Long id) {
        return organizationUnitService.getOrganizationUnit(id);
    }

    @GetMapping("/Unit")
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationUnit> getAllUnits() {
        return organizationUnitService.getAllOrganizationUnits();
    }


}

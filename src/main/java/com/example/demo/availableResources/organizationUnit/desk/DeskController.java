package com.example.demo.availableResources.organizationUnit.desk;


import com.example.demo.availableResources.organizationUnit.AssignableResourceService;
import com.example.demo.availableResources.organizationUnit.ResourceToUnitAssignmentData;
import com.example.demo.user.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;


@RestController
@EnableSwagger2
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class DeskController {

    private final AssignableResourceService<DeskDTO, DeskRecord> deskService;

    @PostMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public Long addDesk(@RequestBody DeskDTO deskDTO) {
        return deskService.add(deskDTO);
    }

    @PutMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public void updateDesk(@RequestBody DeskDTO deskDTO, @RequestParam Long id) {
        deskService.update(deskDTO, id);
    }

    @DeleteMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDesk(@RequestParam Long id) {
        deskService.delete(id);
    }

    @GetMapping("/Desk/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<DeskRecord> getDesk(@PathVariable Long id) {
        return deskService.get(id);
    }

    @GetMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public List<DeskRecord> getAllDesks() {
        AppUser blabla = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//TODO pobieranie akutalnego użytkownika
        return deskService.getAll();

    }

    @PutMapping("/Desk/Assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignDeskToUnit(@RequestBody ResourceToUnitAssignmentData assignmentData) {
        deskService.assignToUnit(assignmentData);
    }
}

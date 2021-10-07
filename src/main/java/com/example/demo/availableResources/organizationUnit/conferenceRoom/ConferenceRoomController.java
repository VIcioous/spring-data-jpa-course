package com.example.demo.availableResources.organizationUnit.conferenceRoom;


import com.example.demo.availableResources.organizationUnit.AssignableResourceService;
import com.example.demo.availableResources.organizationUnit.ResourceToUnitAssignmentData;
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
public class ConferenceRoomController {

    private final AssignableResourceService<ConferenceRoomDTO, ConferenceRoomRecord> conferenceRoomService;

    @PostMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public Long addConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO) {
        return conferenceRoomService.add(conferenceRoomDTO);
    }

    @PutMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public void updateConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO, @RequestParam Long id) {
        conferenceRoomService.update(conferenceRoomDTO, id);
    }

    @DeleteMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConferenceRoom(@RequestParam Long id) {
        conferenceRoomService.delete(id);
    }

    @GetMapping("/ConferenceRoom/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConferenceRoomRecord> getConferenceRoom(@PathVariable Long id) {
        return conferenceRoomService.get(id);
    }

    @GetMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public List<ConferenceRoomRecord> getAllConferenceRoom() {
        return conferenceRoomService.getAll();
    }

    @PutMapping("/ConferenceRoom/Assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignConferenceRoomToUnit(@RequestBody ResourceToUnitAssignmentData assignmentData) {
        conferenceRoomService.assignToUnit(assignmentData);
    }
}

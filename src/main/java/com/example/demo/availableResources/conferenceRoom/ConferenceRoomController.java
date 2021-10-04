package com.example.demo.availableResources.conferenceRoom;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;


@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @PostMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public Long addConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO) {
        return conferenceRoomService.addConferenceRoom(conferenceRoomDTO);
    }

    @PutMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public void updateConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO, @RequestParam Long id) {
        conferenceRoomService.updateConferenceRoom(conferenceRoomDTO, id);
    }

    @DeleteMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConferenceRoom(@RequestParam Long id) {
        conferenceRoomService.deleteConferenceRoom(id);
    }

    @GetMapping("/ConferenceRoom/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConferenceRoomRecord> getConferenceRoom(@PathVariable Long id) {
        return conferenceRoomService.getConferenceRoom(id);
    }

    @GetMapping("/ConferenceRoom")
    @ResponseStatus(HttpStatus.OK)
    public List<ConferenceRoomRecord> getAllConferenceRoom() {
        return conferenceRoomService.getAllConferenceRoom();
    }
}

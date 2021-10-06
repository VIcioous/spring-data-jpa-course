package com.example.demo.availableResources.desk;


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
public class DeskController {

    private final DeskService deskService;

    @PostMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public Long addDesk(@RequestBody DeskDTO deskDTO) {
        return deskService.addDesk(deskDTO);
    }

    @PutMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public void updateDesk(@RequestBody DeskDTO deskDTO, @RequestParam Long id) {
        deskService.updateDesk(deskDTO, id);
    }

    @DeleteMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDesk(@RequestParam Long id) {
        deskService.deleteDesk(id);
    }

    @GetMapping("/Desk/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<DeskRecord> getDesk(@PathVariable Long id) {
        return deskService.getDesk(id);
    }

    @GetMapping("/Desk")
    @ResponseStatus(HttpStatus.OK)
    public List<DeskRecord> getAllDesks() {
        return deskService.getAllDesks();
    }
}

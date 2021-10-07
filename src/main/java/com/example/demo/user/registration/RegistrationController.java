package com.example.demo.user.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@EnableSwagger2
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "Confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}

package com.example.demo.availableResources;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String s) {
        log.info("####SLF4J:: " + s);
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }

}

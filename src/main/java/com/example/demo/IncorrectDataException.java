package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String s) {
        log.info(s);
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,s);
    }

}

package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String s) {
        log.info(s);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,s);
    }
}

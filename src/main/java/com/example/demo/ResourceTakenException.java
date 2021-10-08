package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
public class ResourceTakenException extends RuntimeException{
    public ResourceTakenException(String s)
    {
        log.info(s);
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }
}

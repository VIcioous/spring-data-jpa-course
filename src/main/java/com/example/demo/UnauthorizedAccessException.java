package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException(String s) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}

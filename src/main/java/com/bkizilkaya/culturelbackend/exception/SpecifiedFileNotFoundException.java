package com.bkizilkaya.culturelbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class SpecifiedFileNotFoundException extends RuntimeException{
    public SpecifiedFileNotFoundException(String s, String message) {
        super(message);
    }

    public SpecifiedFileNotFoundException(String message) {
        super(message);
    }
}

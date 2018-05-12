package com.github.punchat.uaa.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailIsNotValidException extends RuntimeException {
    private static final String format = "email %s is not valid";

    public EmailIsNotValidException(String email) {
        super(String.format(format, email));
    }
}

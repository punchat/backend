package com.github.punchat.uaa.registration;

public class AccessCodeIsNotValidException extends RuntimeException {
    private final static String format = "access code %s is not valid for %s";

    public AccessCodeIsNotValidException(String email, String code) {
        super(String.format(format, code, email));
    }
}

package com.github.punchat.uaa.account;

/**
 * @author Alex Ivchenko
 */
public class PasswordsDoNotMatchException extends BadRequestException {
    private final static String MESSAGE = "passwords do not match";

    public PasswordsDoNotMatchException() {
        super(MESSAGE);
    }
}

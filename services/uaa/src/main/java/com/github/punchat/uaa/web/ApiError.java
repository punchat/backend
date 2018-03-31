package com.github.punchat.uaa.web;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ApiError {
    @Setter
    private String message;
    private Map<String, String> fieldErrors = new HashMap<>();

    public void addFieldError(String field, String message) {
        fieldErrors.putIfAbsent(field, message);
    }
}

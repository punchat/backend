package com.github.punchat.am.domain.access;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccessCodeCanNotRefresh extends RuntimeException {
    public AccessCodeCanNotRefresh(Long minutes) {
        super(String.format("Access code can not be refreshed. Please try again after %d minutes", minutes));
    }
}

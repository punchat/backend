package com.github.punchat.uaa.id;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alex Ivchenko
 */
@FeignClient("id")
public interface IdClient {
    @GetMapping("/id")
    long next();
}
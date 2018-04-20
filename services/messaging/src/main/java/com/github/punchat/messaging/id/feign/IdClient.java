package com.github.punchat.messaging.id.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("id")
public interface IdClient {
    @GetMapping("/id")
    long next();
}

package com.github.punchat.messaging.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "id", url = "http://localhost:8881")
public interface StandaloneIdClient {
    @GetMapping("/id")
    long next();
}

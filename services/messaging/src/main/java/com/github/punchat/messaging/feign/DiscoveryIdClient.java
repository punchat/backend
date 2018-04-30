package com.github.punchat.messaging.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("id")
public interface DiscoveryIdClient {
    @GetMapping("/id")
    long next();
}

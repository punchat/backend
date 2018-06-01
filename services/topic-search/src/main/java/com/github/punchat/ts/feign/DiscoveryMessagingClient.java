package com.github.punchat.ts.feign;

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("messaging")
public interface DiscoveryMessagingClient {
    @GetMapping("/messages/broadcast/{id}")
    public BroadcastMessageResponse getBroadcastMessageById(@PathVariable("id") Long id);
}

package com.github.punchat.uaa.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
@EnableFeignClients("com.github.punchat")
public class FeignConfig {
}

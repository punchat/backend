package com.github.punchat.starter.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WebProperties.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfiguration {
    public WebConfiguration() {
        log.info("web configuration enabled");
    }
}

package com.github.punchat.starter.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Configuration
@ComponentScan("com.github.punchat.starter.web.error")
@ConditionalOnWebApplication
public class ValidationErrorHandlingConfiguration {
    public ValidationErrorHandlingConfiguration() {
        log.info("api error configuration enabled");
    }
}

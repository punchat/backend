package com.github.punchat.starter.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alex Ivchenko
 */
@ConfigurationProperties("punchat.validation.handling")
public class WebProperties {
    public boolean enabled = true;
}

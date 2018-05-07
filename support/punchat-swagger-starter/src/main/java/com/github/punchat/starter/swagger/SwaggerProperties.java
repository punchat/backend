package com.github.punchat.starter.swagger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private boolean enabled = true;
    private Security security;

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties(prefix = "security")
    public static class Security {
        private String accessTokenUri;
    }
}

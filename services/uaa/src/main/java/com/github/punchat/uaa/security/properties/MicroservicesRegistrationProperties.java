package com.github.punchat.uaa.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties("uaa")
public class MicroservicesRegistrationProperties {
    private List<Microservice> clients = new ArrayList<>();

    @Getter
    @Setter
    public static class Microservice {
        private String clientId;
        private String clientSecret;
        private List<String> scopes = new ArrayList<>();
        private List<String> grantTypes = new ArrayList<>();
    }
}

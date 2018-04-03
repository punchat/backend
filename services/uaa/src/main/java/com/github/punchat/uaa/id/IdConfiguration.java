package com.github.punchat.uaa.id;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class IdConfiguration {
    private final IdClient idClient;

    public IdConfiguration(IdClient idClient) {
        this.idClient = idClient;
    }

    @Bean
    public IdService idService() {
        return new FeignIdClientAdapter(idClient);
    }
}

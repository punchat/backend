package com.github.punchat.uaa.component;

import com.github.punchat.uaa.id.IdService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
@TestConfiguration
public class ComponentTestConfig {
    @Bean
    public IdService idService() {
        return new IdService() {
            @Override
            public Long next() {
                return new Random().nextLong();
            }
        };
    }
}

package com.github.punchat.starter.json;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class JacksonConfig {
    /**
     * To serialize and deserialize Optional and other Jdk8 types
     */
    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }
}

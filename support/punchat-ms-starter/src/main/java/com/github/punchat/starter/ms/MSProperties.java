package com.github.punchat.starter.ms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ConfigurationProperties("punchat")
public class MSProperties {
    private Discovery discovery;


    @Getter
    @Setter
    @ConfigurationProperties("discovery.client")
    public static class Discovery {
        private boolean enabled = true;
    }
}

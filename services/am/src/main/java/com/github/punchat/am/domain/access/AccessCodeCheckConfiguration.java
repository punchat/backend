package com.github.punchat.am.domain.access;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessCodeCheckConfiguration {
        @Value("${refreshTimeInMinutes:30}")
        private int refreshTimeInMinutes;

        @Value("${accessTimeInMinutes:720}")
        private long accessTimeInMinutes;

        @Bean
        public AccessCodeCheck accessCodeCheck() {
            return new AccessCodeCheck(refreshTimeInMinutes, accessTimeInMinutes);
        }
}

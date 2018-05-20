package com.github.punchat.am.domain.invite.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({DefaultInviteProperties.class})
public class PropertiesConfig {
}

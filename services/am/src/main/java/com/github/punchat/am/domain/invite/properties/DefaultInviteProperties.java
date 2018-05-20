package com.github.punchat.am.domain.invite.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("punchat")
public class DefaultInviteProperties {
    private Admin admin = new Admin();

    @Getter
    @Setter
    public static class Admin {
        private String email;
    }
}

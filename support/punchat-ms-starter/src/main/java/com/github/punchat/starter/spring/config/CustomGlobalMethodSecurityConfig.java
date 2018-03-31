package com.github.punchat.starter.spring.config;

import com.github.punchat.starter.security.context.AuthContext;
import com.github.punchat.starter.spring.support.expressions.CustomMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @author Alex Ivchenko
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomGlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final AuthContext authContext;

    public CustomGlobalMethodSecurityConfig(AuthContext authContext) {
        this.authContext = authContext;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler(authContext);
    }
}

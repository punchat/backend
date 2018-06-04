package com.github.punchat.stomp.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/health**").permitAll()
                .anyRequest().authenticated()
    }
}
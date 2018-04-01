package com.github.punchat.starter.ms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Alex Ivchenko
 */
@EnableDiscoveryClient
@ConditionalOnProperty(value = "punchat.discovery.client.enabled", havingValue = "true")
public class DiscoveryConfig {
}

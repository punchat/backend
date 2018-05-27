package com.github.punchat.id.id;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class IdConfiguration {
    @Value("${workerId:0}")
    private long workerId;

    @Value("${datacenterId:0}")
    private long datacenterId;

    @Value("${sequence:0}")
    private long sequence;

    @Value("${idepoch:1527348444324}")
    private long idepoch;

    @Bean
    public IdService idService() {
        return new IdCenterAdapter(workerId, datacenterId, sequence, idepoch);
    }
}

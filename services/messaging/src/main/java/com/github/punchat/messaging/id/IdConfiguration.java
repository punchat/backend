package com.github.punchat.messaging.id;

import com.github.punchat.messaging.id.feign.IdClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Slf4j
//@Configuration
public class IdConfiguration {
    public IdConfiguration() {
        log.info("id configuration init");
    }

    @Bean
    @Profile("!standalone")
    public IdService idService(IdClient idClient) {
        log.info("feign id service will be used");
        return new IdClientAdapter(idClient);
    }

    @Bean
    @Profile("standalone")
    public IdService idService() {
        log.info("standalone id service will be used");
        return new IdClientStandaloneStub();
    }
}

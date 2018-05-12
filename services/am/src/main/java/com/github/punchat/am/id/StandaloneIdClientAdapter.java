package com.github.punchat.am.id;

import com.github.punchat.am.feign.StandaloneIdClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("standalone")
public class StandaloneIdClientAdapter implements IdService {
    private final StandaloneIdClient idClient;

    public StandaloneIdClientAdapter(StandaloneIdClient idClient) {
        log.info("creating standalone id client adapter {}", idClient);
        this.idClient = idClient;
    }

    @Override
    public long next() {
        return idClient.next();
    }
}
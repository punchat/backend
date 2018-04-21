package com.github.punchat.messaging.id;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!standalone")
public class DiscoveryIdClientAdapter implements IdService {
    private final DiscoveryIdClientAdapter idClient;

    public DiscoveryIdClientAdapter(DiscoveryIdClientAdapter idClient) {
        log.info("creating discovery id client adapter {}", idClient);
        this.idClient = idClient;
    }

    @Override
    public long next() {
        return idClient.next();
    }
}

package com.github.punchat.messaging.id;

import com.github.punchat.messaging.id.feign.IdClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!standalone")
public class IdClientAdapter implements IdService {
    private final IdClient idClient;

    public IdClientAdapter(IdClient idClient) {
        this.idClient = idClient;
    }

    @Override
    public long next() {
        return idClient.next();
    }
}

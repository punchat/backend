package com.github.punchat.messaging.id;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Profile("standalone")
public class IdClientStandaloneStub implements IdService {
    private final AtomicLong id = new AtomicLong(0);

    @Override
    public long next() {
        return id.getAndIncrement();
    }
}

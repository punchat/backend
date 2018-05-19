package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.id.IdService;

import java.util.concurrent.atomic.AtomicLong;

public class MockIdService implements IdService {
    private AtomicLong next = new AtomicLong(0);

    @Override
    public long next() {
        return next.getAndIncrement();
    }
}

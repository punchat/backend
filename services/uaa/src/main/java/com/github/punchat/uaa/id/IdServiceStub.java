package com.github.punchat.uaa.id;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
@Service
public class IdServiceStub implements IdService {
    private final Random random = new Random();

    @Override
    public synchronized Long next() {
        return random.nextLong();
    }
}

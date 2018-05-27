package com.github.punchat.messaging.domain.channel;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.stereotype.Service;

@Trace
@Service
public class DirectChannelFinderImpl implements DirectChannelFinder {
    private final DirectChannelRepository repository;

    public DirectChannelFinderImpl(DirectChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public DirectChannel byUser(User user) {
        return repository.findByUser(user).orElseThrow(() ->
                new RuntimeException("direct channel for user " + user.getId() + " not found"));
    }
}

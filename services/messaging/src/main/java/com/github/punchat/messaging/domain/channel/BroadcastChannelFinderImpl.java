package com.github.punchat.messaging.domain.channel;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Trace
@Service
public class BroadcastChannelFinderImpl implements BroadcastChannelFinder {
    private final BroadcastChannelRepository repository;

    public BroadcastChannelFinderImpl(BroadcastChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public BroadcastChannel byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("channel", id));
    }

    @Override
    public BroadcastChannel byName(String channelName) {
        return repository.findByName(channelName).orElseThrow(() -> new ResourceNotFoundException("channel", channelName));
    }
}

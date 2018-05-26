package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Trace
@Service
public class ChannelInviteFinderImpl implements ChannelInviteFinder {
    private final ChannelInviteRepository repository;

    public ChannelInviteFinderImpl(ChannelInviteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChannelInvite byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("channel invite", id));
    }

    @Override
    public Set<ChannelInvite> byChannel(BroadcastChannel channel) {
        return repository.findByChannel(channel);
    }
}

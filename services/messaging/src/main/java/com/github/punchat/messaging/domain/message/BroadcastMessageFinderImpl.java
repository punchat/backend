package com.github.punchat.messaging.domain.message;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Trace
@AllArgsConstructor
public class BroadcastMessageFinderImpl implements BroadcastMessageFinder {
    private final BroadcastMessageRepository repository;

    @Override
    public BroadcastMessage byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
    }

    @Override
    public List<BroadcastMessage> getLast(BroadcastChannel channel, int limit) {
        return repository.findLast(channel, PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<BroadcastMessage> getBefore(BroadcastChannel channel, Long id, int limit) {
        BroadcastMessage msg = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
        return repository.findBefore(channel, msg.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<BroadcastMessage> getAfter(BroadcastChannel channel, Long id, int limit) {
        BroadcastMessage msg = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
        return repository.findAfter(channel, msg.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }
}

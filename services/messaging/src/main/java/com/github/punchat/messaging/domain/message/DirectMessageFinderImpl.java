package com.github.punchat.messaging.domain.message;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Trace
public class DirectMessageFinderImpl implements DirectMessageFinder {
    private final DirectMessageRepository repo;

    public DirectMessageFinderImpl(DirectMessageRepository repo) {
        this.repo = repo;
    }

    @Override
    public DirectMessage byId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("direct message", id));
    }

    @Override
    public List<DirectMessage> getLast(User user1, User user2, int limit) {
        return repo.findLast(user1, user2, PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<DirectMessage> getBefore(User user1, User user2, DirectMessage anchor, int limit) {
        return repo.findBefore(user1, user2, anchor.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<DirectMessage> getAfter(User user1, User user2, DirectMessage anchor, int limit) {
        return repo.findAfter(user1, user2, anchor.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }
}

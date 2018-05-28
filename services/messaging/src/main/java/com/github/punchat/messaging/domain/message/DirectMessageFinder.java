package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.user.User;

import java.util.List;

public interface DirectMessageFinder {
    DirectMessage byId(Long id);

    List<DirectMessage> getLast(User user1, User user2, int limit);

    List<DirectMessage> getBefore(User user1, User user2, DirectMessage anchor, int limit);

    List<DirectMessage> getAfter(User user1, User user2, DirectMessage anchor, int limit);
}

package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;

public interface DirectChannelFinder {
    DirectChannel byUser(User user);
}

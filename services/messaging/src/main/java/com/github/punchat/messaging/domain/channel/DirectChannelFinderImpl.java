package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserFinder;
import org.springframework.stereotype.Service;

@Service
public class DirectChannelFinderImpl implements DirectChannelFinder {
    private final UserFinder userFinder;
    private final DirectChannelRepository repository;

    public DirectChannelFinderImpl(UserFinder userFinder, DirectChannelRepository repository) {
        this.userFinder = userFinder;
        this.repository = repository;
    }

    @Override
    public DirectChannel getDirectChannel(Long userId) {
        User user = userFinder.byId(userId);
        return repository.findByUser(user);
    }
}

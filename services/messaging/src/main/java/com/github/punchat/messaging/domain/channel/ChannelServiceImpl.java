package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final DirectChannelRepository directChannelRepository;
    private final UserService userService;

    public ChannelServiceImpl(BroadcastChannelRepository broadcastChannelRepository,
                              DirectChannelRepository directChannelRepository, UserService userService) {
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.directChannelRepository = directChannelRepository;
        this.userService = userService;
    }

    @Override
    public DirectChannel getDirectChannel(long userId) {
        User user = userService.getUser(userId);
        return directChannelRepository.findByUser(user);
    }

    @Override
    public BroadcastChannel createBroadcastChannel(BroadcastChannel channel) {
        return null;
    }
}

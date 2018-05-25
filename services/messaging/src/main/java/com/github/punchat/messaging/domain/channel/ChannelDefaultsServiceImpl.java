package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.dto.messaging.channel.Privacy;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Trace
@Service
@AllArgsConstructor
public class ChannelDefaultsServiceImpl implements ChannelDefaultsService {
    private final ChannelService channelService;
    private final UserRepository userRepository;

    @Override
    public Set<BroadcastChannel> createDefaultChannelsFor(User user) {
        Set<BroadcastChannel> created = new HashSet<>();
        if (firstUser()) {
            created.add(createGeneralChannelFor(user));
            created.add(createRandomChannelFor(user));
        }
        return created;
    }

    private boolean firstUser() {
        return userRepository.count() == 1;
    }
    private BroadcastChannel createRandomChannelFor(User user) {
        return createPublicChannelWithName(DefaultChannels.RANDOM, user);
    }

    private BroadcastChannel createGeneralChannelFor(User user) {
        return createPublicChannelWithName(DefaultChannels.GENERAL, user);
    }

    private BroadcastChannel createPublicChannelWithName(String name, User user) {
        BroadcastChannelRequest request = new BroadcastChannelRequest();
        request.setName(name);
        request.setPrivacy(Privacy.PUBLIC);
        return channelService.createFor(user, request);
    }
}

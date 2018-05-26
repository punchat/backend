package com.github.punchat.messaging.events;

import com.github.punchat.events.AccountCreatedEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.ChannelDefaultsService;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Trace
@EnableBinding(Channels.class)
@AllArgsConstructor
public class Events {
    private final UserService userService;
    private final ChannelDefaultsService channelDefaultsService;

    @StreamListener(Channels.ACCOUNT_CREATED_EVENTS)
    public void createUser(AccountCreatedEvent event) {
        User user = userService.createUser(event.getUserId());
        channelDefaultsService.createDefaultChannelsFor(user);
    }
}

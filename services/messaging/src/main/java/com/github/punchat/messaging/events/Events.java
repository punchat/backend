package com.github.punchat.messaging.events;

import com.github.punchat.events.AccountCreatedEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.user.UserService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Trace
@EnableBinding(Channels.class)
public class Events {
    private final UserService userService;

    public Events(UserService userService) {
        this.userService = userService;
    }

    @StreamListener(Channels.ACCOUNT_CREATED_EVENTS)
    public void createUser(AccountCreatedEvent event) {
        userService.createUser(event.getUserId());
    }
}

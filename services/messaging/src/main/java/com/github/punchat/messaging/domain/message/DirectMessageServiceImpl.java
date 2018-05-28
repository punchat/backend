package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageRequest;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.DirectChannel;
import com.github.punchat.messaging.domain.channel.DirectChannelFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserFinder;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Trace
@Service
@AllArgsConstructor
public class DirectMessageServiceImpl implements DirectMessageService {
    private final AuthService authService;
    private final IdService idService;
    private final DirectMessageRepository repo;
    private final UserFinder userFinder;
    private final DirectChannelFinder drChannelFinder;

    @Override
    public DirectMessage create(DirectMessageRequest payload) {
        User sender = authService.getAuthorizedUser();
        User receiver = userFinder.byId(payload.getReceiverId());
        DirectChannel channel = drChannelFinder.byUser(receiver);
        DirectMessage msg = new DirectMessage();
        msg.setId(idService.next());
        msg.setText(payload.getText());
        msg.setSenderUser(sender);
        msg.setChannel(channel);
        msg.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
        return repo.save(msg);
    }
}

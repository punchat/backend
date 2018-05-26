package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.events.NewBroadcastMessageEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.access.PermissionAssertService;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.resource.Resource;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.events.EventBus;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Trace
@Service
@AllArgsConstructor
public class BroadcastMessageServiceImpl implements BroadcastMessageService {
    private final AuthService authService;
    private final IdService idService;
    private final BroadcastChannelFinder bcFinder;
    private final MemberFinder memberFinder;
    private final BroadcastMessageRepository repository;
    private final PermissionAssertService permissions;
    private final EventBus eventBus;

    @Override
    public BroadcastMessage create(BroadcastMessageRequest payload) {
        User authorized = authService.getAuthorizedUser();
        BroadcastChannel channel = bcFinder.byId(payload.getChannelId());
        permissions.checkThat()
                .authorizedUser()
                .canWriteMessages()
                .in(channel);
        Member authorizedMember = memberFinder.byUserAndChannel(authorized, channel);
        Set<Member> addresses = memberFinder.byIds(new HashSet<>(payload.getAddresses()));
        Resource resource = new Resource();
        resource.setId(idService.next());
        resource.setText(payload.getText());
        BroadcastMessage msg = new BroadcastMessage();
        msg.setId(idService.next());
        msg.setAddressees(addresses);
        msg.setChannel(channel);
        msg.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
        msg.setResource(resource);
        msg.setSenderMember(authorizedMember);
        msg = repository.save(msg);
        eventBus.publish(new NewBroadcastMessageEvent(channel.getId(), msg.getId()));
        return msg;
    }
}

package com.github.punchat.am.domain.invite.channel;

import com.github.punchat.am.domain.invite.InviteService;
import com.github.punchat.am.domain.invite.State;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.NewMemberInChannelEvent;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private final ChannelInviteRepository channelInviteRepository;
    private final InviteService inviteService;
    private final AuthContext authContext;

    public ChannelInviteServiceImpl(ChannelInviteRepository channelInviteRepository,
                                    InviteService inviteService,
                                    AuthContext authContext,
                                    EventBus eventBus) {
        this.channelInviteRepository = channelInviteRepository;
        this.inviteService = inviteService;
        this.authContext = authContext;
        this.eventBus = eventBus;
    }

    private final EventBus eventBus;

    public ChannelInvite getInvite(Long recipientUserId, Long channelId) {
        return channelInviteRepository.findByRecipientUserIdAndChannelId(recipientUserId, channelId);
    }

    @Override
    public ChannelInvite createChannelInvite(Long recipientUserId, Long channelId) {
        if (channelInviteRepository.existsByRecipientUserIdAndChannelId(recipientUserId, channelId)) {
            throw new ChannelInviteAlreadyCreated(recipientUserId, channelId);
        }
        ChannelInvite channelInvite = new ChannelInvite(recipientUserId, channelId);
        channelInvite = (ChannelInvite) inviteService.createInvite(channelInvite);
        eventBus.publish(new InviteToChannelEvent(
                channelInvite.getSenderUserId(), channelInvite.getRecipientUserId(),
                channelInvite.getChannelId(), LocalDateTime.now(Clock.systemUTC())));
        return channelInviteRepository.save(channelInvite);
    }

    @Override
    public ChannelInvite acceptChannelInvite(Long channelId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (channelInviteRepository.existsByRecipientUserIdAndChannelId(authUserId, channelId)) {
            ChannelInvite channelInvite = getInvite(authUserId, channelId);
            channelInvite.setState(State.ANSWERED);
            eventBus.publish(new NewMemberInChannelEvent(
                    channelInvite.getSenderUserId(), channelInvite.getRecipientUserId(),
                    channelInvite.getChannelId(), channelInvite.getId()));
            return channelInviteRepository.save(channelInvite);
        } else {
            throw new ChannelInviteDoNotFoundException(channelId, authUserId);
        }
    }
}

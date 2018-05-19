package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private final ChannelInviteRepository channelInviteRepository;
    private final AuthContext authContext;
    private final EventBus eventBus;

    public ChannelInviteServiceImpl(ChannelInviteRepository channelInviteRepository,
                                    AuthContext authContext,
                                    EventBus eventBus) {
        this.channelInviteRepository = channelInviteRepository;
        this.authContext = authContext;
        this.eventBus = eventBus;
    }

    @Override
    public ChannelInvite getInvite(Long recipientUserId, Long channelId) {
        return channelInviteRepository.findByRecipientUserIdAndChannelId(recipientUserId, channelId);
    }

    @Override
    public Set<Long> getUserChannelsInvited(Long userId) {
        return channelInviteRepository.findByRecipientUserIdAndState(userId, State.CREATED)
                .stream()
                .map(ChannelInvite::getChannelId)
                .collect(Collectors.toSet());
    }

    @Override
    public ChannelInvite createChannelInvite(Long recipientUserId, Long channelId) {
        if (channelInviteRepository.existsByRecipientUserIdAndChannelId(recipientUserId, channelId)) {
            throw new ChannelInviteAlreadyCreated(recipientUserId, channelId);
        }
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        ChannelInvite channelInvite = new ChannelInvite(authUserId, recipientUserId, channelId, State.CREATED);
        return channelInviteRepository.save(channelInvite);
    }

    @Override
    public ChannelInvite acceptChannelInvite(Long channelId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (channelInviteRepository.existsByRecipientUserIdAndChannelId(authUserId, channelId)) {
            ChannelInvite channelInvite = getInvite(authUserId, channelId);
            channelInvite.setState(State.ACCEPTED);
            return channelInviteRepository.save(channelInvite);
        } else {
            throw new ChannelInviteDoNotFoundException(channelId, authUserId);
        }
    }
}

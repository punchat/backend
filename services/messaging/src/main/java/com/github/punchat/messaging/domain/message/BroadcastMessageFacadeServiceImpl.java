package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Trace
@Service
@AllArgsConstructor
public class BroadcastMessageFacadeServiceImpl implements BroadcastMessageFacadeService {
    private final BroadcastMessageService msgService;
    private final BroadcastMessageFinder msgFinder;
    private final BroadcastChannelFinder chnFinder;
    private final MessageMapper mapper;

    @Override
    public BroadcastMessageResponse getById(Long id) {
        return map(msgFinder.byId(id));
    }

    @Override
    public BroadcastMessageResponse create(BroadcastMessageRequest payload) {
        return map(msgService.create(payload));
    }

    @Override
    public List<BroadcastMessageResponse> getLast(Long channelId, int limit) {
        return map(msgFinder.getLast(chnFinder.byId(channelId), limit));
    }

    @Override
    public List<BroadcastMessageResponse> getBefore(Long channelId, Long anchor, int limit) {
        return map(msgFinder.getBefore(chnFinder.byId(channelId), anchor, limit));
    }

    @Override
    public List<BroadcastMessageResponse> getAfter(Long channelId, Long anchor, int limit) {
        return map(msgFinder.getAfter(chnFinder.byId(channelId), anchor, limit));
    }

    private BroadcastMessageResponse map(BroadcastMessage message) {
        return mapper.toResponse(message);
    }

    private List<BroadcastMessageResponse> map(List<BroadcastMessage> messages) {
        return messages.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}

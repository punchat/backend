package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.MessageDto;
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
    private final BroadcastMessageFinder msgFinder;
    private final BroadcastChannelFinder chnFinder;
    private final MessageMapper mapper;

    @Override
    public List<MessageDto> getLast(Long channelId, int limit) {
        return map(msgFinder.getLast(chnFinder.byId(channelId), limit));
    }

    @Override
    public List<MessageDto> getBefore(Long channelId, Long anchor, int limit) {
        return map(msgFinder.getBefore(chnFinder.byId(channelId), anchor, limit));
    }

    @Override
    public List<MessageDto> getAfter(Long channelId, Long anchor, int limit) {
        return map(msgFinder.getAfter(chnFinder.byId(channelId), anchor, limit));
    }

    private List<MessageDto> map(List<BroadcastMessage> messages) {
        return messages.stream()
                .map(mapper::messageToMessageDto)
                .collect(Collectors.toList());
    }
}

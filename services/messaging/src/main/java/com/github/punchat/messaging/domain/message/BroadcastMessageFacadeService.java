package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.MessageDto;

import java.util.List;

public interface BroadcastMessageFacadeService {
    List<MessageDto> getLast(Long channelId, int limit);

    List<MessageDto> getBefore(Long channelId, Long anchor, int limit);

    List<MessageDto> getAfter(Long channelId, Long anchor, int limit);
}

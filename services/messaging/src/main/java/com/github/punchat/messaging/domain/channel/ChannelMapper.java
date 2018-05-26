package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import org.mapstruct.*;

@Mapper(componentModel="spring")
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelMapper {
    BroadcastChannel fromRequest(BroadcastChannelRequest payload);

    BroadcastChannelResponse toResponse(BroadcastChannel channel);

    void updateChannel(BroadcastChannelRequest request, @MappingTarget BroadcastChannel channel);
}


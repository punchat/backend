package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.am.messaging.channel.BroadcastChannelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ChannelMapper {

    BroadcastChannel channelDtoToChannel(BroadcastChannelDto channelDto);

    BroadcastChannelDto channelToChannelDto(BroadcastChannel channel);
}


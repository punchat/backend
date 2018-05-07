package com.github.punchat.am.domain.invite;

public class ChannelDoNotFound extends  BadRequestException{
    public ChannelDoNotFound(Long channelId) {
        super(String.format("Channel with id %d don't found", channelId));
    }
}

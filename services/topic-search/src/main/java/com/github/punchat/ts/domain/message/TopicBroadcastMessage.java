package com.github.punchat.ts.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "broadcast_messages")
public class TopicBroadcastMessage extends TopicMessage {

    public TopicBroadcastMessage(Long messageId, List<Topic> topics, Long channelId) {
        super(messageId, topics);
        this.channelId = channelId;
    }

    @JsonIgnore
    @Column(name = "channel_id")
    private Long channelId;
}

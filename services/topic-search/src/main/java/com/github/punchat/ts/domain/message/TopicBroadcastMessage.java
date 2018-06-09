package com.github.punchat.ts.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("broadcast")
public class TopicBroadcastMessage extends TopicMessage {

    public TopicBroadcastMessage(Long messageId, List<Topic> topics, Long channelId) {
        super(messageId, topics);
        this.channelId = channelId;
    }

    @JsonIgnore
    @Column(name = "channel_id")
    private Long channelId;
}

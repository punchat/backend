package com.github.punchat.ts.domain.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class TopicMessage {

    @Id
    @Column(name = "id")
    private Long messageId;

    @Column(name = "channel_id")
    private Long channelId;

    @ElementCollection(targetClass = Topic.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "topics")
    @Column(name = "topic")
    private List<Topic> topics;

}

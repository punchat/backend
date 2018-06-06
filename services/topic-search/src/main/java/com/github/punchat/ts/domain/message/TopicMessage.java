package com.github.punchat.ts.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class TopicMessage {

    @Id
    @Column(name = "id")
    private Long messageId;

    @JsonIgnore
    @ElementCollection(targetClass = Topic.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "topics")
    @Column(name = "topic")
    private List<Topic> topics;

}

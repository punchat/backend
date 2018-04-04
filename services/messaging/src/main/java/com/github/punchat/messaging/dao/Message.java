package com.github.punchat.messaging.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id", "sender_id", "channel_id", "resource_id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressees")
public class Message extends AbstractIdentifiableObject {

    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender_id;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel_id;

    @Column(name = "resource_id")
    private Long resource_id;

    @OneToMany (mappedBy = "message_id")
    private List<Addressee> addressees;
}

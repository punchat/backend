package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.resource.Resource;
import com.github.punchat.messaging.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "messages")
@Entity
public class Message extends AbstractIdentifiableObject {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Embedded
    private Resource resource;
}

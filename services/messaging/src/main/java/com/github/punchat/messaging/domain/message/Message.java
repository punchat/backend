package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.resource.Resource;
import com.github.punchat.messaging.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "messages")
@Entity
@ToString
public class Message extends AbstractIdentifiableObject {
    @Embedded
    private Resource resource;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}

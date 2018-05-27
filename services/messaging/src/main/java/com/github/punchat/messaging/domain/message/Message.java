package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
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
    @Column(name = "text")
    private String text;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}

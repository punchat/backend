package com.github.punchat.messaging.addressee;

import com.github.punchat.messaging.abstractentity.AbstractIdentifiableObject;
import com.github.punchat.messaging.message.Message;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(of = {"id", "message_id"})
@EqualsAndHashCode(of = {"id", "message_id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressees")
public class Addressee extends AbstractIdentifiableObject {

    @ManyToOne
    @JoinColumn (name = "message_id")
    private Message message_id;
}

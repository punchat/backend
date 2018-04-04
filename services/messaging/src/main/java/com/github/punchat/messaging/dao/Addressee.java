package com.github.punchat.messaging.dao;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(of = {"id", "message_id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressees")
public class Addressee extends AbstractIdentifiableObject {

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "message_id")
    private Message message_id;
}

package com.github.punchat.am.domain.invite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.punchat.am.domain.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "invites")
@Entity
public class Invite extends AbstractIdentifiableObject {

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private State state;

    @Column(name = "sender_user_id")
    @JsonIgnore
    private Long senderUserId;
}
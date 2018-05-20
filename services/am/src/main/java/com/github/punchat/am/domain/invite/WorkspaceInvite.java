package com.github.punchat.am.domain.invite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.punchat.am.domain.AbstractIdentifiableObject;
import com.github.punchat.am.domain.access.AccessCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "WORKSPACE_INVITE")
@Entity
public class WorkspaceInvite extends AbstractIdentifiableObject {

    @Column(name = "sender_user_id")
    private Long senderUserId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "access_code_id")
    private AccessCode accessCode = null;
}
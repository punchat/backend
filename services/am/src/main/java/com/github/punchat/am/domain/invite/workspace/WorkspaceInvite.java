package com.github.punchat.am.domain.invite.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.punchat.am.domain.access.AccessCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "workspace")
@Entity
public class WorkspaceInvite extends Invite {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "access_code_id")
    @JsonIgnore
    private AccessCode accessCode = null;
}
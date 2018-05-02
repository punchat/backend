package com.github.punchat.am.domain.access;

import com.github.punchat.am.domain.invite.WorkspaceInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCodeRepository extends JpaRepository<AccessCode, Long> {
    AccessCode findByWorkspaceInvite(WorkspaceInvite invite);
}

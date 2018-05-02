package com.github.punchat.am.domain.invite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceInviteRepository extends JpaRepository<WorkspaceInvite, Long> {
    WorkspaceInvite findByEmail(String email);
}

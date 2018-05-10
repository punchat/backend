package com.github.punchat.am.domain.invite.workspace.dto;

import lombok.*;

@AllArgsConstructor
@Data
public class WorkspaceAccessCodeValidation {
    private String email;
    private String code;
}

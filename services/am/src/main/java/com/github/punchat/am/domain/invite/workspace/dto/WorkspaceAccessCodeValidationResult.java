package com.github.punchat.am.domain.invite.workspace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkspaceAccessCodeValidationResult {
    private String email;
    private String code;
    private AccessCodeValidationResult result;
}

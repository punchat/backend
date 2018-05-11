package com.github.punchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkspaceEmailValidation {
    private String email;
    private WorkspaceEmailValidationResult workspaceEmailValidationResult;
}

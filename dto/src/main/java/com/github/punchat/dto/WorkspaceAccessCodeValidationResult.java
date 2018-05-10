package com.github.punchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkspaceAccessCodeValidationResult {
    private String email;
    private String code;
    private AccessCodeValidationResult result;
}

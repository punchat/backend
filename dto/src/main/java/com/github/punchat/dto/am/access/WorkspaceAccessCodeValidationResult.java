package com.github.punchat.dto.am.access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceAccessCodeValidationResult {
    private String email;
    private String code;
    private AccessCodeValidationResult result;
}

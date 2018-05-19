package com.github.punchat.dto.am.am.access;

import com.github.punchat.dto.am.am.access.AccessCodeValidationResult;
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

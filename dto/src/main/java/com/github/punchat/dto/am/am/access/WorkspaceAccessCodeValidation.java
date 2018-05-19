package com.github.punchat.dto.am.am.access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceAccessCodeValidation {
    private String email;
    private String code;
}

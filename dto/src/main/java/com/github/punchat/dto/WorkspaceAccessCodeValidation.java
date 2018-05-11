package com.github.punchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkspaceAccessCodeValidation {
    private String email;
    private String code;
}

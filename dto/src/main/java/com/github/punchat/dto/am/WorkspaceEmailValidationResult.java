package com.github.punchat.dto.am;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceEmailValidationResult {
    private String email;
    private EmailValidationResult result;
}

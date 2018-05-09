package com.github.punchat.am.domain.invite.workspace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidation {
    private String email;
    private EmailValidationResult emailValidationResult;
}

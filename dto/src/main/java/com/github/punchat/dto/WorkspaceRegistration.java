package com.github.punchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkspaceRegistration {
    String email;
    String username;
    String password;
    String code;
}

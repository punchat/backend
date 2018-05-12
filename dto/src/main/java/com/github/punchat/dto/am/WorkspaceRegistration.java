package com.github.punchat.dto.am;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceRegistration {
    String email;
    String username;
    String password;
    String code;
}

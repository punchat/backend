package com.github.punchat.uaa.registration;

import com.github.punchat.uaa.account.jsr303.Password;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkspaceRegistration {
    @NotNull(message = "{WorkspaceRegistration.email.NotNull}")
    private String email;
    @NotNull(message = "{WorkspaceRegistration.username.NotNull}")
    private String username;
    @Password(message = "{WorkspaceRegistration.password.Password}")
    private String password;
    @NotNull(message = "{WorkspaceRegistration.code.NotNull}")
    private String code;
}

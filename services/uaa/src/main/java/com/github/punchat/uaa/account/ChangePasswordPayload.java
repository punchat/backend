package com.github.punchat.uaa.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.punchat.uaa.account.jsr303.Password;
import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ChangePasswordPayload {
    @Password(message = "{ChangePasswordPayload.oldPassword.required}")
    private final String oldPassword;
    @Password(message = "{ChangePasswordPayload.newPassword.required}")
    private final String newPassword;

    @JsonCreator
    public ChangePasswordPayload(@JsonProperty("oldPassword") String oldPassword, @JsonProperty("newPassword") String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}

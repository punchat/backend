package com.github.punchat.uaa.registration;

public interface AccountManagementService {
    boolean emailIsValid(String email);

    boolean codeIsValid(String email, String code);
}

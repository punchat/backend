package com.github.punchat.uaa.registration;

import com.github.punchat.dto.am.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("feign")
public class AccountManagementFeignClientAdapter implements AccountManagementService {
    private final AccountManagementClient client;

    public AccountManagementFeignClientAdapter(AccountManagementClient client) {
        this.client = client;
    }

    @Override
    public boolean emailIsValid(String email) {
        WorkspaceEmailValidation validation = new WorkspaceEmailValidation(email);
        WorkspaceEmailValidationResult result = client.checkEmail(validation);
        return result.getResult() == EmailValidationResult.VALID;
    }

    @Override
    public boolean codeIsValid(String email, String code) {
        WorkspaceAccessCodeValidation validation = new WorkspaceAccessCodeValidation(email, code);
        WorkspaceAccessCodeValidationResult result = client.checkAccessCode(validation);
        return result.getResult() == AccessCodeValidationResult.VALID;
    }
}

package com.github.punchat.uaa.registration;

import com.github.punchat.dto.am.access.AccessCodeValidationResult;
import com.github.punchat.dto.am.access.WorkspaceAccessCodeValidation;
import com.github.punchat.dto.am.access.WorkspaceAccessCodeValidationResult;
import com.github.punchat.dto.am.invite.EmailValidationResult;
import com.github.punchat.dto.am.invite.WorkspaceEmailValidation;
import com.github.punchat.dto.am.invite.WorkspaceEmailValidationResult;
import com.github.punchat.log.Trace;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Trace
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

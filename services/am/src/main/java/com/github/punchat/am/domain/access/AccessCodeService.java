package com.github.punchat.am.domain.access;

import com.github.punchat.am.domain.invite.workspace.dto.AccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.AccessCodeValidationResult;

public interface AccessCodeService {
    AccessCode generateAccessCode();

    AccessCode refreshAccessCode(AccessCode accessCode);

    AccessCodeValidationResult checkAccessCode(AccessCode accessCode,
                                               AccessCodeValidation accessCodeValidation);
}

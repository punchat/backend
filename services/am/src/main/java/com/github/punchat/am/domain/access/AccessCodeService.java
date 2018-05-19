package com.github.punchat.am.domain.access;

import com.github.punchat.dto.am.am.access.AccessCodeValidationResult;
import com.github.punchat.dto.am.am.access.WorkspaceAccessCodeValidation;

public interface AccessCodeService {
    AccessCode generateAccessCode();

    AccessCode refreshAccessCode(AccessCode accessCode);

    AccessCodeValidationResult checkAccessCode(AccessCode accessCode,
                                               WorkspaceAccessCodeValidation accessCodeValidation);
}

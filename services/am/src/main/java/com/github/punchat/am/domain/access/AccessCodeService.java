package com.github.punchat.am.domain.access;

public interface AccessCodeService {
    AccessCode generateAccessCode();

    boolean checkAccessCode(AccessCode checkCode, AccessCode accessCode);
}

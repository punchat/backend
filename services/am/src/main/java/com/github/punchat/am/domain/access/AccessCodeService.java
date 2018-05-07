package com.github.punchat.am.domain.access;

public interface AccessCodeService {
    AccessCode generateAccessCode(String email);

    boolean checkAccessCode(String email, String code);
}

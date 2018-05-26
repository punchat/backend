package com.github.punchat.messaging.domain.access;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.security.AuthService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Trace
@Service
@Setter
public class PermissionAssertServiceImpl implements PermissionAssertService {
    private AuthService authService;
    private MemberFinder memberFinder;

    @Autowired
    public PermissionAssertServiceImpl(AuthService authService, MemberFinder memberFinder) {
        this.authService = authService;
        this.memberFinder = memberFinder;
    }

    @Override
    public PermissionAssert.UserStageBuilder checkThat() {
        return new PermissionAssertImpl(authService, memberFinder);
    }
}

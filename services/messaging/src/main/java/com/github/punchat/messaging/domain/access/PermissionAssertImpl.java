package com.github.punchat.messaging.domain.access;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.member.NotAMemberException;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.security.AuthService;

public class PermissionAssertImpl implements PermissionAssert, PermissionAssert.UserStageBuilder, PermissionAssert.ChannelStageBuilder, PermissionAssert.PermissionStageBuilder {
    private final AuthService authService;
    private final MemberFinder memberFinder;

    public PermissionAssertImpl(AuthService authService, MemberFinder memberFinder) {
        this.authService = authService;
        this.memberFinder = memberFinder;
    }

    private User user;
    private Permission permission;

    @Override
    public PermissionStageBuilder authorizedUser() {
        user = authService.getAuthorizedUser();
        return this;
    }

    @Override
    public ChannelStageBuilder hasPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public ChannelStageBuilder canWriteMessages() {
        this.permission = Permission.CAN_WRITE_MESSAGES;
        return this;
    }

    @Override
    public ChannelStageBuilder canInviteUsers() {
        this.permission = Permission.CAN_INVITE_USERS;
        return this;
    }

    @Override
    public void in(BroadcastChannel channel) {
        Member member;
        try {
            member = memberFinder.byUserAndChannel(user, channel);
        } catch (ResourceNotFoundException e) {
            throw new NotAMemberException(user.getId(), channel.getName());
        }
        if (!member.hasPermission(permission)) {
            throw new AbsentPermissionException(user.getId(), permission);
        }
    }
}

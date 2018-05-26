package com.github.punchat.messaging.domain.access;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.Permission;

public interface PermissionAssert {
    interface UserStageBuilder {
        PermissionStageBuilder authorizedUser();
    }

    interface PermissionStageBuilder {
        ChannelStageBuilder hasPermission(Permission permission);

        ChannelStageBuilder canWriteMessages();

        ChannelStageBuilder canInviteUsers();
    }

    interface ChannelStageBuilder {
        void in(BroadcastChannel channel);
    }
}

package com.github.punchat.messaging.domain.message;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.role.ForbiddenException;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;

import java.util.List;

@Trace
@AllArgsConstructor
public class SecuredDirectMessageFinder implements DirectMessageFinder {
    private final DirectMessageFinder unsecured;
    private final AuthService authService;

    @Override
    public DirectMessage byId(Long id) {
        DirectMessage msg = unsecured.byId(id);
        checkAccess(msg.getSenderUser(), msg.getChannel().getUser());
        return msg;
    }

    @Override
    public List<DirectMessage> getLast(User user1, User user2, int limit) {
        checkAccess(user1, user2);
        return unsecured.getLast(user1, user2, limit);
    }

    @Override
    public List<DirectMessage> getBefore(User user1, User user2, DirectMessage anchor, int limit) {
        checkAccess(user1, user2);
        return unsecured.getBefore(user1, user2, anchor, limit);
    }

    @Override
    public List<DirectMessage> getAfter(User user1, User user2, DirectMessage anchor, int limit) {
        checkAccess(user1, user2);
        return unsecured.getAfter(user1, user2, anchor, limit);
    }

    private void checkAccess(User user1, User user2) {
        if (authService.isTrusted()) {
            return;
        }
        User authorized = authService.getAuthorizedUser();
        if (!authorized.equals(user1) && !authorized.equals(user2)) {
            throw new ForbiddenException(String.format("user %s cannot get messages between %s and %s", authorized.getId(), user1.getId(), user2.getId()));
        }
    }
}

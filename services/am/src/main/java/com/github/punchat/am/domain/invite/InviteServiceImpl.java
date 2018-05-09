package com.github.punchat.am.domain.invite;

import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;
import com.github.punchat.am.id.IdService;


@Service
public class InviteServiceImpl implements InviteService {
    private final AuthContext authContext;
    private final IdService idService;

    public InviteServiceImpl(AuthContext authContext,
                             IdService idService) {
        this.authContext = authContext;
        this.idService = idService;
    }

    @Override
    public Invite createInvite(Invite invite) {
        Long senderUserId = authContext.get().getUserInfo().get().getUserId();
        invite.setId(idService.next());
        invite.setSenderUserId(senderUserId);
        invite.setState(State.CREATED);
        return invite;
    }
}

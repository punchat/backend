package com.github.punchat.am.domain.invite;

public class InviteDoNotFound extends BadRequestException{

    public InviteDoNotFound(String invite) {
        super(String.format("Invite for email %s don't found", invite));
    }
}

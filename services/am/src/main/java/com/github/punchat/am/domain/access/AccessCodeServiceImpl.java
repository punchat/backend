package com.github.punchat.am.domain.access;

import com.github.punchat.am.domain.invite.*;
import com.github.punchat.am.id.IdService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AccessCodeServiceImpl implements AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final InviteService inviteService;
    private final IdService idService;

    public AccessCodeServiceImpl(AccessCodeRepository accessCodeRepository,
                                 InviteService inviteService,
                                 IdService idService) {
        this.accessCodeRepository = accessCodeRepository;
        this.inviteService = inviteService;
        this.idService = idService;
    }

    @Override
    public String generateAccessCode(String email) {
        AccessCode accessCode = new AccessCode();
        accessCode.setId(idService.next());
        String generatedNumericString = RandomStringUtils.randomNumeric(4);
        accessCode.setCode(generatedNumericString);
        accessCode.setCreationTime(LocalDateTime.now(Clock.systemUTC()));
        accessCode = accessCodeRepository.save(accessCode);
        WorkspaceInvite invite = (WorkspaceInvite) inviteService.getInvite(email);
        invite.setAccessCode(accessCode);
        invite.setState(State.SENT);
        return generatedNumericString;
    }

    @Override
    public boolean checkAccessCode(String email, String code) {
        WorkspaceInvite invite = (WorkspaceInvite) inviteService.getInvite(email);
        AccessCode accessCode = invite.getAccessCode();
        if (accessCode.getCode().equals(code) &&
                ChronoUnit.MINUTES.between(accessCode.getCreationTime(),
                        LocalDateTime.now(Clock.systemUTC())) < 60) {
            invite.setState(State.ACCEPTED);
            return true;
        } else {
            return false;
        }
    }
}

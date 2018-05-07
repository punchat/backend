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
    public AccessCode generateAccessCode(String email) {
        AccessCode accessCode = new AccessCode();
        accessCode.setId(idService.next());
        String generatedNumericString = RandomStringUtils.randomNumeric(4);
        accessCode.setCode(generatedNumericString);
        accessCode.setCreationTime(LocalDateTime.now(Clock.systemUTC()));
        return accessCodeRepository.save(accessCode);
    }

    @Override
    public boolean checkAccessCode(String email, String code) {
        WorkspaceInvite workspaceInvite = inviteService.getInvite(email);
        AccessCode accessCode = workspaceInvite.getAccessCode();
        if (accessCode.getCode().equals(code) &&
                ChronoUnit.MINUTES.between(accessCode.getCreationTime(),
                        LocalDateTime.now(Clock.systemUTC())) < 60) {
            workspaceInvite.setState(State.ACCEPTED);
            return true;
        } else {
            return false;
        }
    }
}

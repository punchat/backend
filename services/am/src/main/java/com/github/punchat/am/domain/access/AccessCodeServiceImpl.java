package com.github.punchat.am.domain.access;

import com.github.punchat.am.domain.invite.*;
import com.github.punchat.am.events.AccessCodesBus;
import com.github.punchat.am.id.IdService;
import com.github.punchat.events.AccessCodeGeneratedEvent;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AccessCodeServiceImpl implements AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final IdService idService;


    public AccessCodeServiceImpl(AccessCodeRepository accessCodeRepository,
                                 InviteService inviteService,
                                 IdService idService) {
        this.accessCodeRepository = accessCodeRepository;
        this.idService = idService;
    }

    @Override
    public AccessCode generateAccessCode() {
        AccessCode accessCode = new AccessCode();
        accessCode.setId(idService.next());
        String generatedNumericString = RandomStringUtils.randomNumeric(4);
        accessCode.setCode(generatedNumericString);
        accessCode.setCreationTime(LocalDateTime.now(Clock.systemUTC()));
        return accessCodeRepository.save(accessCode);
    }

    @Override
    public boolean checkAccessCode(AccessCode checkCode, AccessCode accessCode) {
        if (accessCode.getCode().equals(checkCode.getCode())) {
            if (ChronoUnit.MINUTES.between(accessCode.getCreationTime(),
                    LocalDateTime.now(Clock.systemUTC())) < 60) {
                return true;
            }
        }
        return false;
    }
}

package com.github.punchat.am.domain.access;

import com.github.punchat.am.id.IdService;
import com.github.punchat.dto.am.am.access.AccessCodeValidationResult;
import com.github.punchat.dto.am.am.access.WorkspaceAccessCodeValidation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AccessCodeServiceImpl implements AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;
    private final AccessCodeCheck accessCodeCheck;
    private final IdService idService;

    public AccessCodeServiceImpl(AccessCodeRepository accessCodeRepository,
                                 AccessCodeCheck accessCodeCheck,
                                 IdService idService) {
        this.accessCodeRepository = accessCodeRepository;
        this.accessCodeCheck = accessCodeCheck;
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
    public AccessCode refreshAccessCode(AccessCode accessCode) {
        if (ChronoUnit.MINUTES.between(
                accessCode.getCreationTime(), LocalDateTime.now(Clock.systemUTC()))
                > accessCodeCheck.getRefreshTimeInMinutes()) {
            return generateAccessCode();
        } else {
            throw new AccessCodeCanNotRefresh(accessCodeCheck.getRefreshTimeInMinutes() -
                    ChronoUnit.MINUTES.between(accessCode.getCreationTime(),
                            LocalDateTime.now(Clock.systemUTC())));
        }
    }

    @Override
    public AccessCodeValidationResult checkAccessCode(AccessCode accessCode,
                                                      WorkspaceAccessCodeValidation accessCodeValidation) {
        if (accessCode.getCode().equals(accessCodeValidation.getCode())) {
            if (ChronoUnit.MINUTES.between(
                    accessCode.getCreationTime(), LocalDateTime.now(Clock.systemUTC()))
                    < accessCodeCheck.getAccessTimeInMinutes()) {
                return AccessCodeValidationResult.VALID;
            }
            return AccessCodeValidationResult.OUTDATED;
        }
        return AccessCodeValidationResult.INVALID;
    }
}

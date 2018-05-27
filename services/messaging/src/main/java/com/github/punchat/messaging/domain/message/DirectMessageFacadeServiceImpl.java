package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageRequest;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.user.UserFinder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Trace
@Service
@Slf4j
public class DirectMessageFacadeServiceImpl implements DirectMessageFacadeService {
    private final DirectMessageFinder drMsgFinder;
    private final DirectMessageService drMsgService;
    private final DirectMessageMapper drMsgMapper;
    private final UserFinder userFinder;

    public DirectMessageFacadeServiceImpl(@NonNull DirectMessageFinder drMsgFinder,
                                          @NonNull DirectMessageService drMsgService,
                                          @NonNull DirectMessageMapper drMsgMapper,
                                          @NonNull UserFinder userFinder) {
        this.drMsgFinder = drMsgFinder;
        this.drMsgService = drMsgService;
        this.drMsgMapper = drMsgMapper;
        this.userFinder = userFinder;
    }

    @Override
    public DirectMessageResponse getById(Long id) {
        return map(drMsgFinder.byId(id));
    }

    @Override
    public DirectMessageResponse create(DirectMessageRequest payload) {
        return map(drMsgService.create(payload));
    }

    @Override
    public List<DirectMessageResponse> getLast(Long user1Id, Long user2Id, int limit) {
        return map(drMsgFinder.getLast(userFinder.byId(user1Id), userFinder.byId(user2Id), limit));
    }

    @Override
    public List<DirectMessageResponse> getBefore(Long user1Id, Long user2Id, Long anchor, int limit) {
        return map(drMsgFinder.getBefore(userFinder.byId(user1Id), userFinder.byId(user2Id), drMsgFinder.byId(anchor), limit));
    }

    @Override
    public List<DirectMessageResponse> getAfter(Long user1Id, Long user2Id, Long anchor, int limit) {
        return map(drMsgFinder.getAfter(userFinder.byId(user1Id), userFinder.byId(user2Id), drMsgFinder.byId(anchor), limit));
    }

    private DirectMessageResponse map(DirectMessage message) {
        return drMsgMapper.toResponse(message);
    }

    private List<DirectMessageResponse> map(List<DirectMessage> messages) {
        return messages.stream()
                .map(drMsgMapper::toResponse)
                .collect(Collectors.toList());
    }
}

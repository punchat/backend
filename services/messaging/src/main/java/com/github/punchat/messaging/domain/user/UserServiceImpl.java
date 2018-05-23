package com.github.punchat.messaging.domain.user;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.DirectChannel;
import com.github.punchat.messaging.domain.channel.DirectChannelRepository;
import com.github.punchat.messaging.id.IdService;
import org.springframework.stereotype.Service;

@Trace
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DirectChannelRepository directChannelRepository;
    private final IdService idService;

    public UserServiceImpl(UserRepository userRepository, DirectChannelRepository directChannelRepository, IdService idService) {
        this.userRepository = userRepository;
        this.directChannelRepository = directChannelRepository;
        this.idService = idService;
    }

    @Override
    public User createUser(Long id) {
        User user = new User();
        DirectChannel channel = new DirectChannel();

        channel.setId(idService.next());
        user.setId(idService.next());

        user.setChannel(channel);
        channel.setUser(user);

        user = userRepository.save(user);
        directChannelRepository.save(channel);
        return user;
    }
}

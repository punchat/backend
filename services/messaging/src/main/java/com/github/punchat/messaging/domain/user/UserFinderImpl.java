package com.github.punchat.messaging.domain.user;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Trace
@Service
public class UserFinderImpl implements UserFinder {
    private final UserRepository userRepository;

    public UserFinderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User byId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", id));
    }
}

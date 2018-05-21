package com.github.punchat.messaging.security;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.UserInfoIsNotProvidedException;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserRepository;
import com.github.punchat.starter.uaa.client.auth.UserInfo;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;

@Trace
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthContext authContext;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthContext authContext, UserRepository userRepository) {
        this.authContext = authContext;
        this.userRepository = userRepository;
    }

    @Override
    public User getAuthorizedUser() throws UserInfoIsNotProvidedException {
        UserInfo userInfo = authContext.get().getUserInfo().orElseThrow(UserInfoIsNotProvidedException::new);
        return userRepository.findById(userInfo.getUserId()).get();
    }
}

package com.github.punchat.starter.spring.support.args;

import com.github.punchat.starter.security.auth.Auth;
import com.github.punchat.starter.security.context.AuthContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Alex Ivchenko
 */
public class AuthMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final AuthContext auth;

    public AuthMethodArgumentResolver(AuthContext auth) {
        this.auth = auth;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return auth.get();
    }
}

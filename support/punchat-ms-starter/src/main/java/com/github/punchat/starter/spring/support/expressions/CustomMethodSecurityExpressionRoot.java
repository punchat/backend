package com.github.punchat.starter.spring.support.expressions;

import com.github.punchat.starter.security.context.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private final static String TRUSTED_SCOPE = "server";
    private AuthContext authContext;
    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(AuthContext authContext, Authentication authentication) {
        super(authentication);
        this.authContext = authContext;
    }

    public boolean hasTrustedScope() {
        return authContext.get().getScope().contains(TRUSTED_SCOPE);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}

package com.paipai.security.auth;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

public class SessionArgumentResolver implements HandlerMethodArgumentResolver {

    private AuthorizationContext authorizationContext;

    public SessionArgumentResolver(AuthorizationContext authorizationContext) {
        this.authorizationContext = authorizationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.getParameterType().equals(HSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String token = webRequest.getHeader(AuthKey.AUTH_HEADER);
        AuthKey authKey = AuthKey.fromToken(token);
        if(Objects.nonNull(authKey)) {
            return authorizationContext.getSession(authKey.getSessionId());
        }
        return null;
    }
}

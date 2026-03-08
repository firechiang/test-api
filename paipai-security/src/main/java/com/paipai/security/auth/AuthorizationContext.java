package com.paipai.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paipai.security.WebConstants;
import com.paipai.security.res.RestRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthorizationContext implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    private static final ConcurrentHashMap<String, HSession<?>> sessionMap = new ConcurrentHashMap<>(64);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(AuthKey.AUTH_HEADER);
        AuthKey authKey = AuthKey.fromToken(token);
        if (Objects.nonNull(authKey)) {
            HSession<?> session = sessionMap.get(authKey.getSessionId());
            if (Objects.nonNull(session) && token.equals(session.getToken())) {
                long currentTime = Instant.now().getEpochSecond();
                if (currentTime < session.getExpiredTime()) {
                    return true;
                }
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(WebConstants.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(RestRes.error("未登陆授权!")));
        return false;

    }

    public void addSession(HSession<?> session) {
        if (Objects.isNull(session) || Objects.isNull(session.getId())) {
            throw new NullPointerException("Session or Session ID is Null.");
        }
        sessionMap.put(session.getId(), session);
    }

    public HSession<?> getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}

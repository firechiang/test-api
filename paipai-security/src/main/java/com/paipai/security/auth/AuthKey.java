package com.paipai.security.auth;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@Getter
@ToString
public class AuthKey {

    public static final String AUTH_HEADER = "authorization";
    private static final String SEPARATOR = "-";
    private static final Pattern PATTERN = Pattern.compile(".*-\\d+$");

    private String sessionId;

    private String tokenId;

    private AuthKey(String tokenId, String sessionId) {
        this.tokenId = tokenId;
        this.sessionId = sessionId;
    }

    public String toToken() {

        return String.join(SEPARATOR, this.getTokenId(), this.getSessionId());
    }

    public static AuthKey fromSessionId(String sessionId) {
        AuthKey authKey = new AuthKey(UUID.randomUUID().toString(), sessionId);
        return authKey;
    }

    public static AuthKey fromToken(String token) {
        if (Objects.nonNull(token)) {
            boolean matches = PATTERN.matcher(token).matches();
            if (matches) {
                int lastIndex = token.lastIndexOf(SEPARATOR);
                String tokenId = token.substring(0, lastIndex);
                String sessionId = token.substring(lastIndex + 1);
                return new AuthKey(tokenId, sessionId);
            }
        }
        return null;
    }
}

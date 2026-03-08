package com.paipai.security.auth;

import lombok.Data;

/**
 * 用户会话
 */
@Data
public class HSession<T> {

    /**
     * 用户信息
     */
    private T user;

    /**
     * Session ID
     */
    private String id;

    /**
     * Token
     */
    private String token;

    /**
     * 过期时间
     */
    private long expiredTime;

    public HSession(T user, String id, String token, long expiredTime) {
        this.user = user;
        this.id = id;
        this.token = token;
        this.expiredTime = expiredTime;
    }
}

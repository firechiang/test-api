package com.paipai.admin.api.controller;

import com.paipai.security.auth.AuthKey;
import com.paipai.security.auth.AuthorizationContext;
import com.paipai.security.auth.HSession;
import com.paipai.security.res.RestRes;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthorizationContext authorizationContext;

    @GetMapping("/admin")
    public RestRes<Map<String, Object>> authInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "猫猫");
        map.put("age", 18);
        map.put("avatar", "https://www.baidu.com/sdfdsf/dfdsfs");
        return RestRes.success(map);
    }

    @PostMapping("/login")
    public ResponseEntity<RestRes<String>> login(@RequestBody LoginParam param) {
        System.out.println(param);
        String sessionId = "1";
        AuthKey authKey = AuthKey.fromSessionId(sessionId);
        String token = authKey.toToken();
        long expiredTime = LocalDateTime.now().plusDays(7L).atZone(ZoneId.systemDefault()).toEpochSecond();
        HSession<String> session = new HSession<String>("", sessionId, token, expiredTime);
        authorizationContext.addSession(session);
        return ResponseEntity.ok(RestRes.success(token));
    }

}

@Data
class LoginParam {

    private String uname;

    private String pwd;
}

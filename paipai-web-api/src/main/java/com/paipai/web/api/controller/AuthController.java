package com.paipai.web.api.controller;

import com.paipai.core.service.AuthService;
import com.paipai.core.service.PUserService;
import com.paipai.data.entity.PUser;
import com.paipai.security.auth.AuthKey;
import com.paipai.security.auth.AuthorizationContext;
import com.paipai.security.auth.HSession;
import com.paipai.security.res.RestRes;
import com.paipai.security.validator.Phone;
import com.paipai.web.api.vo.SUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@Tag(name = "登陆注册API")
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private AuthService authService;

    @Autowired
    private PUserService puserService;

    @Autowired
    private AuthorizationContext authorizationContext;

    @Operation(summary = "密码登陆")
    @ApiResponse(responseCode = "200", description = "成功 result 字段返回 token", content = @Content(schema = @Schema(implementation = RestRes.RestResString.class)))
    @PostMapping(value = "/login/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestRes<String>> loginPwd(@Valid @RequestBody UserLoginParam param) {
        PUser puser = puserService.queryByPhone(param.getPhone());
        if (Objects.nonNull(puser)) {
            boolean isPwd = pe.matches(param.getPwd(), puser.getPwd());
            if (isPwd) {
                SUser suser = new SUser();
                BeanUtils.copyProperties(puser, suser);
                String sessionId = puser.getId().toString();
                AuthKey authKey = AuthKey.fromSessionId(sessionId);
                String token = authKey.toToken();
                long expiredTime = LocalDateTime.now().plusDays(7L).atZone(ZoneId.systemDefault()).toEpochSecond();
                HSession<SUser> session = new HSession<SUser>(suser, sessionId, token, expiredTime);
                authorizationContext.addSession(session);
                return ResponseEntity.ok(RestRes.success(token));
            }
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(RestRes.error("用户不存在或密码错误！"));
    }

    @Operation(summary = "用户注册")
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC)
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC, content = @Content(schema = @Schema(implementation = RestRes.RestResInteger.class)))
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestRes<Integer>> register(@Valid @RequestBody UserRegParam param) {
        String pwd = pe.encode(param.getPwd());
        boolean res = authService.register(param.getPhone(), pwd);
        if (res) {
            return ResponseEntity.ok(RestRes.success());
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(RestRes.error("用户已存在！"));
    }
}

@Data
@Schema(description = "用户登陆参数")
class UserLoginParam {

    @Phone
    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 24, message = "密码长度必须在8到24个字符之间")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pwd;

}

@Data
@Schema(description = "用户注册参数")
class UserRegParam {

    @Phone
    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码长度必须是6个字符")
    @Schema(description = "验证码（随便填写6个字符即可）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String vcode;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 24, message = "密码长度必须在8到24个字符之间")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pwd;
}

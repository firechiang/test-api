package com.paipai.web.api.controller;

import com.paipai.core.service.PUserService;
import com.paipai.data.entity.PUser;
import com.paipai.security.auth.HSession;
import com.paipai.security.res.RestRes;
import com.paipai.security.validator.Phone;
import com.paipai.web.api.vo.SUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户相关API")
public class PUserController {

    @Autowired
    private PUserService puserService;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Operation(summary = "个人信息")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RestResUser.class)))
    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<SUser> info(@Parameter(hidden = true) HSession<SUser> session) {
        return RestRes.success(session.getUser());
    }

    @Operation(summary = "密码修改")
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC, content = @Content(schema = @Schema(implementation = RestRes.RestResInteger.class)))
    @PostMapping(value = "/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestRes<Integer>> upPwd(@Valid @RequestBody UpPwdParam param,
            @Parameter(hidden = true) HSession<SUser> session) {
        if (!session.getUser().getPhone().equals(param.getPhone())) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(RestRes.error("手机号码异常！"));
        }
        String pwd = pe.encode(param.getPwd());
        PUser puser = PUser.builder().id(session.getUser().getId()).pwd(pwd).build();
        puserService.modifyByPrimaryKeySelective(puser);
        return ResponseEntity.ok(RestRes.success());
    }

    @Operation(summary = "修改个人信息")
    @ApiResponse(responseCode = "200", description = RestRes.SUCCESS_DESC)
    @PostMapping(value = "/up", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestRes<Integer> up(@Valid @RequestBody UUser uuser, @Parameter(hidden = true) HSession<SUser> session) {
        PUser puser = PUser.builder().build();
        BeanUtils.copyProperties(uuser, puser);
        puser.setId(session.getUser().getId());
        puserService.modifyUserByPrimaryKey(puser);
        return RestRes.success();
    }
}

@Data
@Schema(name = "修改个人信息参数")
class UUser {

    @Schema(description = "昵称")
    private String nickName;

    @Min(value = 0, message = "性别不能小于0！")
    @Max(value = 2, message = "性别不能大于2！")
    @Schema(description = "性别（0 未知，1 女，2 男）")
    private Integer sex;

    @Schema(description = "爱好")
    private String hobby;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省份")
    private String prov;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "地址")
    private String addr;

    @Schema(description = "简介")
    private String introduction;
}

@Data
@Schema(name = "密码修改参数")
class UpPwdParam {

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

@Schema(name = "用户信息")
class RestResUser extends RestRes<SUser> {
}

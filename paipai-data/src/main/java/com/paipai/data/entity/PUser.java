package com.paipai.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 前端用户
 */
@Builder
@Data
public class PUser {

    @Hidden
    @JsonIgnore
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Hidden
    @JsonIgnore
    @Schema(description = "密码")
    private String pwd;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "性别")
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

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "创建时间")
    private long createTime;

}

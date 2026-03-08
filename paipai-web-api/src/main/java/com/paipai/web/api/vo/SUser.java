package com.paipai.web.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SUser {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "性别（0 未知，1 女，2 男）")
    private int sex;

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

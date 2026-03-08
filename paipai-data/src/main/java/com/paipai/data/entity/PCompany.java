package com.paipai.data.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 公司表
 */
@Data
@Builder
public class PCompany {

    private Long id;

    @Schema(description = "公司名称")
    private String cname;

    @Schema(description = "成立时间")
    private String createDate;

    @Schema(description = "网址")
    private String website;

    @Schema(description = "登记编号")
    private String regNumber;

    @Schema(description = "公司规模")
    private String csize;

    @Schema(description = "实缴资本")
    private String capital;

    @Schema(description = "业务类型")
    private String business;

    @Schema(description = "服务人群")
    private String crowd;

    @Schema(description = "信息来源名称")
    private String sourceN;

    @Schema(description = "信息来源网址")
    private String sourceW;

    @Schema(description = "更新时间")
    private Long updateTime;
}
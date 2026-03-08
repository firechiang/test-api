package com.paipai.data.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目表
 */
@Data
@Builder
public class PProject {

    private Long id;

    @Schema(description = "所属公司")
    private Long companyId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "项目Logo")
    private String logo;

    @Schema(description = "项目评分")
    private BigDecimal score;

    @Schema(description = "订阅数量")
    private Integer subCount;

    @Schema(description = "评论数量")
    private Integer commCount;

    @Schema(description = "点赞数量")
    private Integer goodCount;

    @Schema(description = "业务类型")
    private String business;

    @Schema(description = "项目地址")
    private String addr;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "项目详情")
    private String detail;

    @Schema(description = "创建时间")
    private Long createTime;
}
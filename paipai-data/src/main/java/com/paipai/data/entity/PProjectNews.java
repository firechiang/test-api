package com.paipai.data.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 项目动态表
 */
@Data
@Builder
public class PProjectNews {

    private Long id;

    @Schema(description = "所属项目")
    private Long projectId;

    @Schema(description = "动态标题")
    private String title;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "动态详情")
    private String detail;

    @Schema(description = "创建时间")
    private Long createTime;

}
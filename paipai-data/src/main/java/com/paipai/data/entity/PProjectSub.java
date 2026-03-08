package com.paipai.data.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 项目订阅表
 */
@Data
@Builder
public class PProjectSub {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "项目ID", type = "string")
    private Long projectId;

    @Schema(description = "创建时间")
    private Long createTime;

}
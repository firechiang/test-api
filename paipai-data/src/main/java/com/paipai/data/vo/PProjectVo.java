package com.paipai.data.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PProjectVo {

    private Long id;

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

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "创建时间")
    private Long createTime;
}

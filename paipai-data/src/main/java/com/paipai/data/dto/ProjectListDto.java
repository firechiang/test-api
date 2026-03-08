package com.paipai.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(name = "项目列表参数")
public class ProjectListDto extends Pageable {

    @Setter
    @Getter
    @Schema(description = "关键字搜索（暂时只匹配项目名称）")
    private String keywords;
}

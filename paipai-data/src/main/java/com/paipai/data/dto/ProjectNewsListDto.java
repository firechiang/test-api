package com.paipai.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Schema(name = "项目动态列表参数")
@EqualsAndHashCode(callSuper = true)
public class ProjectNewsListDto extends Pageable {

    @Setter
    @Getter
    @NotNull(message = "项目ID不能为空")
    @Schema(description = "项目ID")
    private Long projectId;
}

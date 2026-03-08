package com.paipai.data.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Pageable {

    // Page index must not be less than zero
    @Schema(description = "页码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pageNumber = 1;

    // Page size must not be less than one
    @Schema(description = "页面大小", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pageSize = 1;

    @Hidden
    public Long getOffset() {

        return (this.pageNumber.longValue() - 1) * this.pageSize.longValue();
    }

    public void setPageNumber(Integer pageNumber) {
        if (Objects.isNull(pageNumber) || pageNumber < 0) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (Objects.isNull(pageSize) || pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }
}

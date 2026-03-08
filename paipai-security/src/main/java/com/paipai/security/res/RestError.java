package com.paipai.security.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RestError {

    @Schema(description = "错误码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String errorCode;

    @Schema(description = "错误信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String errorMsg;

    public RestError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RestError(String errorMsg, String errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}

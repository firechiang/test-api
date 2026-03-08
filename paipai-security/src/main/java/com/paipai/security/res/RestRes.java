package com.paipai.security.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "响应")
public class RestRes<T> {

    public static final Integer SUCCESS_RES = 1;

    public static final String SUCCESS_DESC = "成功 result 字段返回数值 1";

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private T result;

    @Schema(description = "错误", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private RestError error;

    public static RestRes<Integer> success() {
        return RestRes.success(SUCCESS_RES);
    }

    public static <T> RestRes<T> success(T result) {
        RestRes<T> restResponse = new RestRes<>();
        restResponse.setResult(result);
        return restResponse;
    }

    public static <T> RestRes<T> error(String errorMsg) {
        RestRes<T> restResponse = new RestRes<>();
        restResponse.setError(new RestError(errorMsg));
        return restResponse;
    }

    @Schema(name = "String响应")
    public static class RestResString extends RestRes<String> {
    }

    @Schema(name = "Integer响应")
    public static class RestResInteger extends RestRes<Integer> {
    }

}

package com.paipai.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 机构资质类型枚举
 */
@Getter
@AllArgsConstructor
public enum AgencyCategoryType {

    /**
     * 私募管理人
     */
    PRIVATE_FUND_MANAGER(1),
    /**
     * 三方销售机构
     */
    THIRD_PARTY_SALES(2);

    /**
     * 类型编码
     */
    private final Integer code;

    /**
     * 根据code获取枚举
     */
    public static AgencyCategoryType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (AgencyCategoryType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

package com.paipai.data.entity;

import lombok.Data;

@Data
public class PAgency {

    /**
     * 机构ID
     */
    private Long id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * Logo图片
     */
    private String logoUrl;

    /**
     * 机构简介
     */
    private String summary;

    /**
     * 资质类型（1, 私募管理人,2 三方销售机构）
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Long createdAt;
}
